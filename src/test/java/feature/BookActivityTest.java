package feature;

import java.net.MalformedURLException;
import java.util.ArrayList;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import core.BaseTest;
import core.Common;
import core.Connection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import object.BookActivity;
import object.KeySearch;
import tutorial_appium.AuthClass;
import tutorial_appium.homeTest;
import ultis.FileTest;

public class BookActivityTest extends BaseTest {

	AndroidDriver andDriver;
	ArrayList<BookActivity> bookActivities;
	AuthClass ac;
	FileTest ft;
	Common cmn;
	homeTest ht;
	
    @BeforeTest
    public void beforeTest() throws MalformedURLException, InterruptedException {
        System.out.println("Before test BookActivityTest");

	    ft = new FileTest();
	    bookActivities = ft.ReadBookActivity();

		andDriver = Connection.getConnectionMainAndroid();
		cmn = new Common(andDriver);
		Thread.sleep(3000);
		WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Hoạt động giải trí\"]"));
		signupKhachsan.click();
		Thread.sleep(2000);	
		WebElement input = andDriver.findElement(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
		input.sendKeys("Hà Nội");
		Thread.sleep(1000);
		WebElement textFind = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\"Hà Nội\").instance(1)"));
		textFind.click();
		Thread.sleep(2000);
		andDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(1)).setAsHorizontalList().scrollIntoView(new UiSelector().textContains(\"Skip the Line\"))"));
		WebElement skip = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Skip the Line\")"));
		skip.click();
		Thread.sleep(1000);
		WebElement showTalent = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Xem khả năng đáp ứng\")"));
		showTalent.click();
		Thread.sleep(1000);
		WebElement selectDate = cmn.getElementBy(By.xpath("//*[@resource-id='com.tripadvisor.tripadvisor:id/viewDoubleField']"));
		selectDate.click();
    }
    
    @Test
    public void test() throws InterruptedException {
    	for(int i = 0; i < bookActivities.size(); i++) {
			if (i <= 1) {
				cmn.scrollToMonth(bookActivities.get(i).getMonth(), andDriver);
				WebElement selectDay = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\""+bookActivities.get(i).getDate()+"\n"
						+ "241 N\")"));
				selectDay.click();
				try {
					WebElement resText = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView"));
					boolean res = resText.getText().contains(bookActivities.get(i).getExpect());
					System.out.println("Result : " + i + " " + res);
					Assert.assertTrue(res);
					ft.write(i + 68, 7, "Pass");	
				} catch (Exception e) {
					// TODO: handle exception
					ft.write(i + 68, 7, "Failed");	
					continue;
				}
			} else {
				WebElement selectPeople = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.TextView[1]"));
				selectPeople.click();
				Thread.sleep(1000);
				WebElement btnSub = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageView"));
				WebElement btnAdd = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ImageView"));
				if (i == 2) {
					btnSub.click();
					btnSub.click();
					Thread.sleep(1000);
					String actual = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtCount")).getText();
					try {
						System.out.println("Result : " + i + " " + !btnSub.isEnabled());
						Assert.assertFalse(btnSub.isEnabled());
						ft.write(i + 68, 7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
						ft.write(i + 68, 7, "Faild");
						continue;
					}
				} else {
					btnAdd.click();
					btnAdd.click();
					Thread.sleep(1000);
					boolean res = false;
					
					if (i == 4) {
						WebElement apply = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary"));
						apply.click();
						Thread.sleep(1000);
						WebElement bookNow = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\"Đặt chỗ ngay\")"));
						bookNow.click();
						Thread.sleep(4000);
						if (cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().text(\"" +bookActivities.get(i).getExpect()+ "\")"))) {
							res = true;
						}
					} else {
						String actual = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtCount")).getText();
						res = bookActivities.get(i).getExpect().contains(actual);						
					}
					System.out.println("Result : " + i + " " + res);
					
					try {
						Assert.assertTrue(res);
						ft.write(i + 68, 7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
						ft.write(i + 68, 7, "Faild"); 
						continue;
					}
				}
				Thread.sleep(1000);
			}
		}
		System.out.println("Finish");
    }
    
//    @AfterMethod
//	public void tearDownTest() {
//		System.out.println("Quit driver");
//		andDriver.quit();
//	}
}
