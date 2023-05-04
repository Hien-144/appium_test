package tutorial_appium;
//khởi chạy
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class BookOne {

	static AndroidDriver andDriver;
	static FileTest ft;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		ft = new FileTest();
		
		ArrayList<BookRoomOne> readBook = ft.ReadBookRoomOne();
		ArrayList<BookPeopleOne> readPeople = ft.readBookPeopleOne();
		runBook(readBook, true);
		runPeole(readPeople);
		reSet();
		apply();
//		for (int i=0; i < readBook.size() ; i++) {
//			System.out.println(readBook.get(i).getFrom()+ readBook.get(i).getMonthFrom() + readBook.get(i).getTo() + readBook.get(i).getMonthTo());
//		}
		System.out.println("cccccccccc");
		
	}
	
	public static void runBook(ArrayList<BookRoomOne> readBooks, boolean check) throws MalformedURLException, InterruptedException {
		andDriver = Connection.getConnectionMainAndroid();
		Common cmn = new Common(andDriver);
		BookRoomOneTest brot = new BookRoomOneTest(andDriver);
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
			boolean res = brot.homeBook(readBooks.get(i), check);
			if (check) {
				if (res) {
					ft.write(i + 17, 7, "Pass");
				} else {
					ft.write(i + 17, 7, "Failed");	
				}
				brot.scrollBack("5", cmn);
			}
			Thread.sleep(500);
		}		
	}
	
	public static void runPeole(ArrayList<BookPeopleOne> bookPeopleOnes) throws InterruptedException, MalformedURLException  {
		andDriver = Connection.getConnectionMainAndroid();
		Common cmn = new Common(andDriver);
		BookRoomOneTest brot = new BookRoomOneTest(andDriver);
		
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
						Assertions.assertFalse(btnSub.isEnabled());
						ft.write(i + 22, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						e.printStackTrace();
						ft.write(i + 22, 7, "Faildffff");
						continue;
					}
				} else {
					btnAdd.click();
					String actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[2]\n"
							+ "")).getText();
					try {
						Assertions.assertTrue(bookPeopleOnes.get(i).getExpect().contains(actual));
						ft.write(i + 22, 7, "Pass");
					} catch (AssertionFailedError e) {
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
						Assertions.assertFalse(btnSub.isEnabled());
						ft.write(i + 22, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 22, 7, "Faild");
						continue;
					}
				} else {
					String actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[2]")).getText();
					if (Integer.parseInt(actual) <= 1) {
						System.out.println("peoplepeople");
						btnAdd.click();
					}
					actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[2]")).getText();
					try {
						Assertions.assertTrue(bookPeopleOnes.get(i).getExpect().contains(actual));
						ft.write(i + 22, 7, "Pass");
					} catch (AssertionFailedError e) {
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
						Assertions.assertFalse(btnSub.isEnabled());
						ft.write(i + 22, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 22, 7, "Faild");
						continue;
					}
				} else {
					btnAdd.click();
					String actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView[2]")).getText();
					try {
						Assertions.assertTrue(bookPeopleOnes.get(i).getExpect().contains(actual));
						ft.write(i + 22, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 22, 7, "Faild");
						continue;
					}
				}
			}
		}
	}
	
	public static void reSet() throws MalformedURLException, InterruptedException {
		BookRoomOne broreset = ft.readLine(29);
		System.out.println(broreset.getExpect());
		
		andDriver = Connection.getConnectionMainAndroid();
		Common cmn = new Common(andDriver);
		BookRoomOneTest brot = new BookRoomOneTest(andDriver);
		
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
			Assertions.assertTrue(actual.equalsIgnoreCase(broreset.getExpect()));
			ft.write(29, 7, "Pass");
		} catch (junit.framework.AssertionFailedError e) {
			// TODO: handle exception
			ft.write(29, 7, "Faild");
		}
	}

	private static void apply() throws MalformedURLException, InterruptedException {
		ArrayList<BookRoomOne> bros = new ArrayList<BookRoomOne>();
		Common cmn = new Common(andDriver);
		
		BookRoomOne bookRoomOne = ft.readLine(30);
		bros.add(bookRoomOne);
		WebElement selectDay = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout"));
		selectDay.click();
		
		
		runBook(bros, false);
		andDriver = Connection.getConnectionMainAndroid();
		
		WebElement applyBtn = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary"));
		applyBtn.click();
		 
		WebElement check1 = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[1]/android.widget.CompoundButton[2]"));
		String actual = check1.getText();
		
		System.out.println(actual + " " + bookRoomOne.getExpect());
		try {
			Assertions.assertTrue(bookRoomOne.getExpect().contains(actual));
			ft.write(30, 7, "Pass");
		} catch (Exception e) {
			ft.write(30, 7, "Faild");
			// TODO: handle exception
		}
	}

}
