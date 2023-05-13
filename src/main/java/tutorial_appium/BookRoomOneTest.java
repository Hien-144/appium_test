package tutorial_appium;
//thực thi 
//verify ngày đặt phòng

import java.awt.Point;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.opentest4j.AssertionFailedError;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class BookRoomOneTest {
	
		WebDriver driver;
		AndroidDriver andDriver;
		FileTest ft = new FileTest();
		public BookRoomOneTest(AndroidDriver andDriver) {
//			super(driver);
			this.andDriver = andDriver;
		}
		@SuppressWarnings("deprecation")
		public boolean homeBook(BookRoomOne bookRoomOne, boolean check) throws InterruptedException  {
			Common cmn = new Common(andDriver);
			Actions action = new Actions(andDriver);
//			ArrayList<WebElement> test = (ArrayList<WebElement>) cmn.getElementsBy(By.id("com.tripadvisor.tripadvisor:id/txtDay"));
			this.scrollToMonth(bookRoomOne.getMonthFrom(), cmn);
//			andDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"tháng 7 năm 2023\"))"));
			WebElement ts = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\""+ bookRoomOne.getFrom() +"\")"));
			ts.click();
			ts.click();
			Thread.sleep(500);
			this.scrollBack("5", cmn);
			Thread.sleep(500);
			this.scrollToMonth(bookRoomOne.getMonthTo(), cmn);
			
			WebElement ts1 = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\""+ bookRoomOne.getTo() +"\")"));
			
			ts1.click();
			Thread.sleep(300);
			if (check) {
				if (cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/txtDescription"))) {
					WebElement expiration = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtTitle"));
					String actual = expiration.getText();
					boolean res = actual.equalsIgnoreCase(bookRoomOne.getExpect());
					try {
						Assertions.assertTrue(res);
						WebElement cancel = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/imgCircularBtnIcon"));
						cancel.click();
						Thread.sleep(500);
						return true;
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						return false;
					}
				} else {
					WebElement result = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtStartInputText"));
					String actual = result.getText();
					boolean res = actual.equalsIgnoreCase(bookRoomOne.getExpect());
					try {
						Assertions.assertTrue(res);
						Thread.sleep(500);
						return true;
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						return false;
					}
				}
			} else {
				return false;
			}
//			andDriver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"tháng 6\"))"));
//
//			WebElement ts1 = andDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text("+ '"' + "15" + '"' +")"));
//			ts1.click();
//			return true;
		}

		public void scrollToMonth(String monthFrom, Common cmn) {
			while (!cmn.isElementPresentAndroidAndroid(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"tháng " + monthFrom + "\")")) 
				&& !cmn.isElementPresentAndroidAndroid(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"tháng " + (Integer.parseInt(monthFrom) + 1) + "\")"))) {
					andDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollForward(20)"));
				}
			WebElement month = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"tháng " + monthFrom + "\")"));
			if (month != null) {
				WebElement end = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/weekDays"));
				int centerX = month.getRect().x + (month.getSize().width / 2);
				double startY = month.getRect().y;
				double endY = end.getRect().y + (end.getSize().height * 1.5);
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence sequence = new Sequence(finger, 1);
				sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, (int) startY));
				sequence.addAction(finger.createPointerDown(0));
				sequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, (int)endY));
				sequence.addAction(finger.createPointerUp(0));
				andDriver.perform(Arrays.asList(sequence));
//				Thread.sleep(100);			
			}
		}
	public void scrollBack(String month, Common cmn) {
		while (!cmn.isElementPresentAndroidAndroid(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"tháng " + month + "\")"))) {
					andDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollBackward(20)"));
				}
	}
}
