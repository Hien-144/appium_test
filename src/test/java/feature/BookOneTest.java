package feature;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import core.BaseTest;
import core.Common;
import core.Connection;
import io.appium.java_client.android.AndroidDriver;
import object.BookActivity;
import object.BookPeopleOne;
import object.BookRoomOne;
import tutorial_appium.AuthClass;
import tutorial_appium.BookRoomOneTest;
import tutorial_appium.homeTest;
import ultis.FileTest;

public class BookOneTest extends BaseTest {

	AndroidDriver andDriver;
	ArrayList<BookActivity> bookActivities;
	AuthClass ac;
	FileTest ft;
	Common cmn;
	homeTest ht;
	ArrayList<BookRoomOne> readBooks;
	ArrayList<BookPeopleOne> bookPeopleOnes;
	BookRoomOneTest brot;
	
    @BeforeTest
    public void beforeTest() throws MalformedURLException, InterruptedException {
        System.out.println("Before test BookOneTest");

	    ft = new FileTest();
		readBooks = ft.ReadBookRoomOne();
		bookPeopleOnes = ft.readBookPeopleOne();

		andDriver = Connection.getConnectionMainAndroid();
		cmn = new Common(andDriver);
		brot = new BookRoomOneTest(andDriver);
    }
    
    @Test
    public void testBook() throws InterruptedException {
		if (!cmn.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView"))) {
			WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Khách sạn\"]"));
			signupKhachsan.click();
			Thread.sleep(2000);
			
			if(cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"))) {
				WebElement input = andDriver.findElement(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
				input.sendKeys("Hà Nội");
				Thread.sleep(1000);
				WebElement testFind = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
				testFind.click();
				Thread.sleep(3000);
				WebElement testFind1 = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[1]/android.widget.CompoundButton[2]"));
				testFind1.click();	
			}
		}
		for (int i = 0; i < readBooks.size() ; i++) {
			boolean check = true;
			boolean res = brot.homeBook(readBooks.get(i), check);
			System.out.println("Result : " + i + " " + res);
			if (res) {
				ft.write(i + 17, 7, "Pass");
			} else {
				ft.write(i + 17, 7, "Failed");	
			}
			brot.scrollBack("5", cmn);
			Thread.sleep(500);
		}
    }
    
    @Test
    public void testPeople() throws InterruptedException {
    	Thread.sleep(5000);
		if(cmn.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[1]/android.widget.CompoundButton[2]"))) {
			WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Khách sạn\"]"));
			signupKhachsan.click();
			Thread.sleep(2000);
			WebElement input = andDriver.findElement(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
			input.sendKeys("Hà Nội");
			Thread.sleep(1000);
			WebElement testFind = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
			testFind.click();
			Thread.sleep(3000);
			WebElement testFind1 = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[1]/android.widget.CompoundButton[2]"));
			testFind1.click();
			Thread.sleep(500);
		}
		WebElement selectPeople = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.TextView[1]"));
		selectPeople.click();
		for (int i=0;i< bookPeopleOnes.size();i++) {
			if (i < 2) { // roomroom
				WebElement btnSub = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ImageView"));
				WebElement btnAdd = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.ImageView"));
				if (!bookPeopleOnes.get(i).getExpectDisable()) {
					try {
						System.out.println("Result : " + i + " " + !btnSub.isEnabled());
						Assert.assertFalse(btnSub.isEnabled());
						ft.write(i + 22, 7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						ft.write(i + 22, 7, "Faild");
						continue;
					}
				} else {
					btnAdd.click();
					String actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[2]\n"
							+ "")).getText();
					try {
						System.out.println("Result : " + i + " " + bookPeopleOnes.get(i).getExpect().contains(actual));
						Assert.assertTrue(bookPeopleOnes.get(i).getExpect().contains(actual));
						ft.write(i + 22, 7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
						ft.write(i + 22, 7, "Faild");
						continue;
					}
				}
			} else if (i > 1 && i < 4) { // peoplepeople
				WebElement btnSub = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView"));
				WebElement btnAdd = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.ImageView"));
				if (Integer.parseInt(cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[2]")).getText()) > 1) {
					btnSub.click();
				}
				if (!bookPeopleOnes.get(i).getExpectDisable()) {
					try {
						System.out.println("Result : " + i + " " + !btnSub.isEnabled());
						Assert.assertFalse(btnSub.isEnabled());
						ft.write(i + 22, 7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
						ft.write(i + 22, 7, "Faild");
						continue;
					}
				} else {
					String actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[2]")).getText();
					if (Integer.parseInt(actual) <= 1) {
						btnAdd.click();
					}
					actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[2]")).getText();
					try {
						System.out.println("Result : " + i + " " + bookPeopleOnes.get(i).getExpect().contains(actual));
						Assert.assertTrue(bookPeopleOnes.get(i).getExpect().contains(actual));
						ft.write(i + 22, 7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
						ft.write(i + 22, 7, "Faild");
						continue;
					}
				}
			} else { // babybaby
				WebElement btnSub = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.ImageView"));
				WebElement btnAdd = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.widget.ImageView"));
				if (!bookPeopleOnes.get(i).getExpectDisable()) {
					try {
						System.out.println("Result : " + i + " " + !btnSub.isEnabled());
						Assert.assertFalse(btnSub.isEnabled());
						ft.write(i + 22, 7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
						ft.write(i + 22, 7, "Faild");
						continue;
					}
				} else {
					btnAdd.click();
					String actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView[2]")).getText();
					try {
						System.out.println("Result : " + i + " " + bookPeopleOnes.get(i).getExpect().contains(actual));
						Assert.assertTrue(bookPeopleOnes.get(i).getExpect().contains(actual));
						ft.write(i + 22, 7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
						ft.write(i + 22, 7, "Faild");
						continue;
					}
				}
			}
		}
    }

    
    @Test
    public void testApply() throws InterruptedException {
		ArrayList<BookRoomOne> bros = new ArrayList<BookRoomOne>();
		BookRoomOne bookRoomOne = ft.readLine(30);
		bros.add(bookRoomOne);
		if (!cmn.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView"))) {
			WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Khách sạn\"]"));
			signupKhachsan.click();
			Thread.sleep(2000);
			
			if(cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"))) {
				WebElement input = andDriver.findElement(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
				input.sendKeys("Hà Nội");
				Thread.sleep(1000);
				WebElement testFind = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
				testFind.click();
				Thread.sleep(3000);
				WebElement testFind1 = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[1]/android.widget.CompoundButton[2]"));
				testFind1.click();	
			}
		}
		for (int i = 0; i < readBooks.size() ; i++) {
			boolean res = brot.homeBook(readBooks.get(i), false);
		}
		Thread.sleep(500);

		WebElement applyBtn = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary"));
		applyBtn.click();
		 
		WebElement check1 = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[1]/android.widget.CompoundButton[2]"));
		String actual = check1.getText();
		
		try {
			System.out.println("Result apply : " + bookRoomOne.getExpect().contains(actual));
			Assert.assertTrue(bookRoomOne.getExpect().contains(actual));
			ft.write(30, 7, "Pass");
		} catch (Exception e) {
			ft.write(30, 7, "Faild");
			// TODO: handle exception
		}
    	
    }
  
    @Test
    public void testReset() throws InterruptedException {
		BookRoomOne broreset = ft.readLine(29);
    	Thread.sleep(5000);
		if(!cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/bdlBtnSecondary"))) {
			WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Khách sạn\"]"));
			signupKhachsan.click();
			Thread.sleep(2000);
		
			WebElement input = andDriver.findElement(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
			input.sendKeys("Hà Nội");
			Thread.sleep(1000);
			WebElement testFind = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
			testFind.click();
			Thread.sleep(3000);
			WebElement testFind1 = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[1]/android.widget.CompoundButton[2]"));
			testFind1.click();
			Thread.sleep(500);
		}
		WebElement selectDay = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout"));
		selectDay.click();
		WebElement reset = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/bdlBtnSecondary"));
		reset.click();
		WebElement text = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView"));
		String actual;
		actual = text.getText();
		try {
			System.out.println("Result reset : " + actual.equalsIgnoreCase(broreset.getExpect()));
			Assert.assertTrue(actual.equalsIgnoreCase(broreset.getExpect()));
			ft.write(29, 7, "Pass");
		} catch (Exception e) {
			// TODO: handle exception
			ft.write(29, 7, "Faild");
			return;
		}
    }
    
//    @AfterTest
//	public void tearDownTest() {
//		System.out.println("Quit driver");
//		andDriver.quit();
//	}
}























