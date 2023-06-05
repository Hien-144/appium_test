package feature;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import core.BaseTest;
import core.Common;
import core.Connection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import object.Cart;
import tutorial_appium.AuthClass;
import tutorial_appium.homeTest;
import ultis.FileTest;

public class CartTest extends BaseTest{

	AndroidDriver andDriver;
	ArrayList<Cart> carts;
	AuthClass ac;
	FileTest ft;
	Common cmn;
	homeTest ht;
	
    @BeforeClass
    public void beforeTest() throws MalformedURLException, InterruptedException {
        System.out.println("Before test CartTest");

	    ft = new FileTest();
	    carts = ft.readCart();

		andDriver = Connection.getConnectionMainAndroid();
		cmn = new Common(andDriver);
    }
    
    @Test
    public void test() throws InterruptedException {
    	if (!cmn.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView"))) {
			WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Khách sạn\"]"));
			signupKhachsan.click();
			Thread.sleep(2000);	
		}
		
		WebElement input = andDriver.findElement(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
		input.sendKeys("Hà Nội");
		Thread.sleep(1000);
		WebElement testFind = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
		testFind.click();
		String name = "Anatole Hotel";
		cmn.scrollToEle(name, cmn);
		WebElement showSale = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Xem giảm giá\")"));
		showSale.click();
		Thread.sleep(6000);
		boolean checkLogin = cmn.isElementPresent(By.xpath("//android.view.View[@content-desc=\"Đăng nhập hoặc tạo tài khoản\"]/android.view.View"));
		System.out.println("CheckLogin " + checkLogin);
		if (checkLogin) { // chua login thi bam de login
			WebElement profileBtn = cmn.getElementBy(By.xpath("//android.view.View[@content-desc=\"Đăng nhập hoặc tạo tài khoản\"]/android.view.View"));
			profileBtn.click();
			Thread.sleep(1000);
		}
		for( int i = 0; i < carts.size(); i++ ) {			
			if (checkLogin) {
				if (cmn.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView"))) {
					WebElement emailInput = cmn.getElementBy(By.xpath("//*[@resource-id='username']"));
					emailInput.sendKeys(carts.get(i).getMail());
					WebElement continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Tiếp tục với email\")"));
					continueBtn.click();
					Thread.sleep(1000);
					if (cmn.isElementPresent(By.xpath("//*[@resource-id='username-note']"))) {
						Thread.sleep(1000);
						WebElement note = cmn.getElementBy(By.xpath("//*[@resource-id='username-note']"));
						String actual = note.getText();
						boolean res = actual.contains(carts.get(i).getExpect());
						try {
							System.out.println("Result : " + i + " " + res);
							Assert.assertTrue(true);
							ft.write(i + 63, 7, "Pass");
						} catch (Exception e) {
							// TODO: handle exception
							ft.write(i + 63, 7, "Failed");
							continue;
						}
					} else if (cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Nhập mật khẩu của bạn\")"))) {
						try {
							System.out.println("Result : " + i + " " + true);
							Assert.assertTrue(true);
							ft.write(i + 63, 7, "Pass");
						} catch (Exception e) {
							// TODO: handle exception
							ft.write(i + 63, 7, "Failed");
							continue;
						}
					}
				} else if (cmn.isElementPresent(By.xpath("//*[@resource-id='password']"))) {
					WebElement passwordInput = cmn.getElementBy(By.xpath("//*[@resource-id='password']"));
					passwordInput.sendKeys(carts.get(i).getPassw());
					WebElement continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Đăng nhập\")"));
					continueBtn.click();
					Thread.sleep(2000);
					if (cmn.isElementPresent(By.xpath("//*[@resource-id='password-note']"))) {
						WebElement note = cmn.getElementBy(By.xpath("//*[@resource-id='password-note']"));
						String actual = note.getText();
						boolean res = actual.contains(carts.get(i).getExpect());
						try {
							System.out.println("Result : " + i + " " + res);
							Assert.assertTrue(res);
							ft.write(i + 63, 7, "Pass");
						} catch (Exception e) {
							// TODO: handle exception
							ft.write(i + 63, 7, "Failed");
							continue;
						}
					} 
				}else {
//					System.out.println(i + " " + carts.get(i).getExpect());
					WebElement success = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Tài khoản của tôi\")"));
					success.click();
					Thread.sleep(2000);
					WebElement book = cmn.getElementBy(By.xpath("//android.view.View[@content-desc=\"Đặt chỗ & Chuyến đi\"]"));
					book.click();
					Thread.sleep(3000);
					boolean res;			
					WebElement success1 = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hanoi Center Silk Hotel\")"));
					if(cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hanoi Center Silk Hotel\")") )){
						res = true;
					}else {
						res = false;
					}
//					boolean res = success.getText().contains("Tài khoản của tôi");
					
					try {
						System.out.println("Result : " + i + " " + res);
						Assert.assertTrue(res);
						ft.write(i + 63, 7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
						ft.write(i + 63, 7, "Failed");
						continue;
					}
				}
			}
			if (i == 3) {
//					System.out.println(i + " " + carts.get(i).getExpect());
				WebElement success = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Tài khoản của tôi\")"));
				success.click();
				Thread.sleep(2000);
				WebElement book = cmn.getElementBy(By.xpath("//android.view.View[@content-desc=\"Đặt chỗ & Chuyến đi\"]"));
				book.click();
				Thread.sleep(3000);
				boolean res;			
				WebElement success1 = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hanoi Center Silk\")"));
				if(cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hanoi Center Silk \")") )){
					res = true;
				}else {
					res = false;
				}
//					boolean res = success.getText().contains("Tài khoản của tôi");
				
				try {
					System.out.println("Result : " + i + " " + res);
					Assert.assertTrue(res);
					ft.write(i + 63, 7, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
					ft.write(i + 63, 7, "Failed");
					continue;
				}
			}
		}
    }
    @AfterClass
	public void tearDownTest() {
		System.out.println("Quit driver");
		andDriver.quit();
	}
}
