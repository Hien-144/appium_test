package test;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.AssertionFailedError;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import core.Common;
import core.Connection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import object.BookActivity;
import object.BookRoomTwo;
import tutorial_appium.AuthClass;
import tutorial_appium.BookRoomTwoTest;
import tutorial_appium.homeTest;
import ultis.FileTest;

public class BookTwoTest {

	AndroidDriver andDriver;
	ArrayList<BookRoomTwo> readBookRoomTwo;
	AuthClass ac;
	FileTest ft;
	Common cmn;
	homeTest ht;
	
    @BeforeTest
    public void beforeTest() throws MalformedURLException, InterruptedException {
        System.out.println("Before test");

	    ft = new FileTest();
	    readBookRoomTwo = ft.readBookRoomTwo();

		andDriver = Connection.getConnectionMainAndroid();
		cmn = new Common(andDriver);
    }
    
    @Test
    public void test() throws InterruptedException {
    	BookRoomTwoTest brot2 = new BookRoomTwoTest(andDriver);
		
		if (!cmn.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView"))) {
			WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Khách sạn\"]"));
			signupKhachsan.click();
			Thread.sleep(2000);	
		}
		if(cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"))) {
			WebElement input = andDriver.findElement(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
			input.sendKeys("Hà Nội");
			Thread.sleep(1000);
			WebElement testFind = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
			testFind.click();
			String name = "Lotte Hotel Hanoi";
			cmn.scrollToEle(name, andDriver);
			WebElement showSale = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Xem giảm giá\")"));
			showSale.click();
//			AndroidDriver
			WebDriverWait wait = new WebDriverWait(andDriver, null);
//			wait.until(Excepted)
			Thread.sleep(5000);
			WebElement filter = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[1]/android.widget.Button[2]"));
			filter.click();
//			System.out.println(filter.getText());
			Thread.sleep(2000);
			WebElement cancelFree = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Miễn phí hủy\")"));
			cancelFree.click();
			Thread.sleep(3000);
			WebElement applyFree= cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hiển thị\")"));
			applyFree.click();
			String nameHotel = "Hanoi Center Silk Hotel & Travel";  
			Thread.sleep(2000);
			cmn.scrollToEle(nameHotel, andDriver);
			WebElement hotel = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ nameHotel +"\")"));
			hotel.click();
			Thread.sleep(500);
			WebElement bookRoom1 = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Đặt phòng cho đêm nay\")"));
//			System.out.println(bookRoom1.getText());
			bookRoom1.click();
			Thread.sleep(2000);
			cmn.scrollToEleText("Đặt phòng", andDriver);
			WebElement bookRoom = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\"Đặt phòng\")"));
//			System.out.println(bookRoom.getText());
			bookRoom.click();
			Thread.sleep(3000);
			String nextStep= "Bước tiếp theo";
			cmn.scrollToEle(nextStep, andDriver);
			WebElement nextStepEle = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+nextStep+"\")"));
			nextStepEle.click();
//			System.out.println(nextStepEle.getText());
			Thread.sleep(5000);
			String nameCheck = "thông tin chi tiết của bạn";
			cmn.scrollToEle(nameCheck, andDriver);
			WebElement nextCheckEle = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+nameCheck+"\")"));
//			nextCheckEle.click();
////			thông tin chi tiết của bạn
			boolean assertRes = false;
			WebElement outClickElement; 
			WebElement focusElement; 
//			System.out.println("Sout res : " + assertRes);
			for(int i = 0; i < readBookRoomTwo.size(); i++) {
//					System.out.println(test.getText());
				if (i >= 5 && i <= 9) { // lastname 
					focusElement = cmn.getElementBy(By.xpath("//*[@resource-id='firstname']"));
					outClickElement = cmn.getElementBy(By.xpath("//*[@resource-id='lastname']"));
					focusElement.clear();
					focusElement.click();
					focusElement.sendKeys(readBookRoomTwo.get(i).getName()); // readBookRoomTwo.get(i).getName() + "test"
//					Thread.sleep(2000);
//					focusElement.click();
					outClickElement.click();
					Thread.sleep(1000);
					assertRes = cmn.isElementPresent(By.xpath("//*[@resource-id='form-field__helper--firstname']"));
//					System.out.println("Sout res : " + assertRes);
				} else if (i <= 4) {
					focusElement = cmn.getElementBy(By.xpath("//*[@resource-id='lastname']"));
					outClickElement = cmn.getElementBy(By.xpath("//*[@resource-id='firstname']"));
					focusElement.clear();
					focusElement.click();
					focusElement.sendKeys(readBookRoomTwo.get(i).getFirstName()); // readBookRoomTwo.get(i).getName() + "test"
//					Thread.sleep(2000);
//					focusElement.click();
					outClickElement.click();
					Thread.sleep(1000);
					assertRes = cmn.isElementPresent(By.xpath("//*[@resource-id='form-field__helper--lastname']"));
//					System.out.println("Sout res : " + assertRes);
				} else if (i >= 10 && i <= 15) {
					focusElement = cmn.getElementBy(By.xpath("//*[@resource-id='email']"));
					outClickElement = cmn.getElementBy(By.xpath("//*[@resource-id='firstname']"));
					focusElement.clear();
					focusElement.click();
					focusElement.sendKeys(readBookRoomTwo.get(i).getEmail()); // readBookRoomTwo.get(i).getName() + "test"
//					Thread.sleep(2000);
//					focusElement.click();
					outClickElement.click();
					Thread.sleep(1000);
					assertRes = cmn.isElementPresent(By.xpath("//*[@resource-id='form-field__helper--email']"));
//					System.out.println("Sout res : " + assertRes);
				} else if (i == 16) {
//					andDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\"cc1\"))"));
					cmn.scrollToEle("Vùng/quốc gia", andDriver);
//					String country = focusElement.getText();
					assertRes = cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ readBookRoomTwo.get(i).getExpect() +"\")"));	
				} else if (i == 17) {
					focusElement = cmn.getElementBy(By.xpath("//*[@resource-id='cc1']"));
					focusElement.click();
					Thread.sleep(2000);
					WebElement countryEle = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[1]")); //"+readBookRoomTwo.get(i).getCountry()+"
					countryEle.click();
					String country = focusElement.getText();
					System.out.println("country : " + readBookRoomTwo.get(i).getCountry());
					assertRes = cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ readBookRoomTwo.get(i).getExpect() +"\")"));
				} else if (i == 20) {
					focusElement = cmn.getElementBy(By.xpath("//*[@resource-id='cc1']"));
					focusElement.click();
					Thread.sleep(2000);
					WebElement countryEle = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]")); //"+readBookRoomTwo.get(i).getCountry()+"
					countryEle.click();
					String country = focusElement.getText();
//					System.out.println("country : " + readBookRoomTwo.get(i).getCountry());
					assertRes = cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ readBookRoomTwo.get(i).getExpect() +"\")"));
				} else if ((i >= 21 && i <= 24) || (i >= 18 && i <= 19)) {
					focusElement = cmn.getElementBy(By.xpath("//*[@resource-id='phone_number']"));
					outClickElement = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Có, tôi muốn xác nhận điện tử miễn phí\")"));
//					outClickElement = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Cần thiết để chỗ nghỉ xác nhận đặt phòng của bạn\")"));
					focusElement.clear();
					focusElement.click();
					focusElement.sendKeys(readBookRoomTwo.get(i).getPhone()); // readBookRoomTwo.get(i).getName() + "test"
//					Thread.sleep(2000);
//					focusElement.click();
					outClickElement.click();
					Thread.sleep(2000);
//					if(i >= 21 && i < 24) {
//						WebElement tsst = cmn.getElementBy(By.xpath("//*[@resource-id='form-field__helper--phone']"));
//						System.out.println(tsst.getText());
////						assertRes = tsst.getText().contains(readBookRoomTwo.get(i).getExpect());
//					}
//					if (i == 23) {
//						return;
//					}
					assertRes = cmn.isElementPresent(By.xpath("//*[@resource-id='form-field__helper--phone']"));
//					System.out.println("Sout res : " +i+ assertRes + " " + readBookRoomTwo.get(i).getPhone().toString() + "cccc");
				}
				if (i < 25) {
					try {	
						System.out.println("Resutl : " + i + " " + assertRes);
						if (readBookRoomTwo.get(i).getExpect().isBlank()) {
							Assertions.assertFalse(assertRes);	
						} else {
							Assertions.assertTrue(assertRes);
						}
//						System.out.println("Res " + readBookRoomTwo.get(i).getName() + " " + i + " " + assertRes);
						ft.write(i + 32, 7, "Pass");
//						nameEle.clear();
					} catch (AssertionFailedError e) {
//						 TODO: handle exception
						ft.write(i + 32, 7, "Failed");
//						nameEle.clear();
						continue;
					}
				}
			}
			
			
			String nextStepEnd= "Đến bước cuối cùng";
			cmn.scrollToEle(nextStepEnd, andDriver);
			WebElement nextStepEleEnd = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+nextStepEnd+"\")"));
			nextStepEleEnd.click();
//			System.out.println(nextStepEleEnd.getText());
			Thread.sleep(1000);
			String nextStepEnd1= "Hoàn tất đặt chỗ";
			cmn.scrollToEle(nextStepEnd1, andDriver);
			WebElement nextStepEleEnd1 = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+nextStepEnd1+"\")"));
			nextStepEleEnd1.click();
			System.out.println(nextStepEleEnd1.getText());
			Thread.sleep(5000);
			String nextStepEnd2= "Xem tiếp xác nhận trên web";
			cmn.scrollToEle(nextStepEnd2, andDriver);
			WebElement nextStepEleEnd2 = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+nextStepEnd2+"\")"));
			nextStepEleEnd2.click();
			Thread.sleep(3000);
//			if (cmn.isElementPresent(By.xpath("//*[@resource-id='add-password']"))) {
				WebElement addPass= cmn.getElementBy(By.xpath("//*[@resource-id='add-password']"));
				WebElement createBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\"Tạo mật khẩu\")"));
				for(int i = 0; i< readBookRoomTwo.size(); i ++) {
					if (i >= 25) {
//						System.out.println("cc " + readBookRoomTwo.get(i).getCreatePass());
						addPass.sendKeys(readBookRoomTwo.get(i).getCreatePass());
						createBtn.click();
						boolean res = false;
						if (i < 30) {
							WebElement errorEle = cmn.getElementBy(By.xpath("//*[@resource-id='password-error']"));
							String actual = errorEle.getText();
//							System.out.println(actual);
//							System.out.println(readBookRoomTwo.get(i).getExpect());
							res = actual.contains(readBookRoomTwo.get(i).getExpect());
							
						} else { // nếu đúng mk thì không hiển thị phần tử lỗi
							if (!cmn.isElementPresent(By.xpath("//*[@resource-id='password-error']"))) {
								res = true;
							}
						}
						try {
//							System.out.println("Result : " + i + " " + res);
							Assertions.assertTrue(res);
							ft.write(i + 32, 7, "Pass");
						} catch (AssertionFailedError e) {
							// TODO: handle exception
							ft.write(i + 32, 7, "Failed");
						}
						
					}
				}
//			}
			Thread.sleep(1000);
		}
    }

}
