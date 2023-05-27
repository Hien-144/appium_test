package tutorial_appium;
//khởi chạy thực thi chức năng hoạt động giải trí
//verify hoạt động giải trí
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.opentest4j.AssertionFailedError;

import core.Common;
import core.Connection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import object.BookActivity;
import ultis.FileTest;

public class BookActivityTest {
	static AndroidDriver andDriver;
	static FileTest ft;

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		ft = new FileTest();
		ArrayList<BookActivity> bookActivities = ft.ReadBookActivity();
//		System.out.println(bookActivities.size());
		test(bookActivities);
	}
	
	public static void test(ArrayList<BookActivity> bookActivities) throws MalformedURLException, InterruptedException {
		andDriver = Connection.getConnectionMainAndroid();
		Common cmn = new Common(andDriver);
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
		for(int i = 0; i < bookActivities.size(); i++) {
			if (i <= 1) {
				scrollToMonth(bookActivities.get(i).getMonth(), cmn);
				WebElement selectDay = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\""+bookActivities.get(i).getDate()+"\n"
						+ "240 N\")"));
				selectDay.click();
				try {
					WebElement resText = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView"));
					boolean res = resText.getText().contains(bookActivities.get(i).getExpect());
					System.out.println("Result : " + i + " " + res);
					Assertions.assertTrue(res);
					ft.write(i + 68, 7, "Pass");	
				} catch (AssertionFailedError e) {
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
						Assertions.assertFalse(btnSub.isEnabled());
						ft.write(i + 68, 7, "Pass");
					} catch (AssertionFailedError e) {
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
						Assertions.assertTrue(res);
						ft.write(i + 68, 7, "Pass");
					} catch (AssertionFailedError e) {
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

	public static void scrollToMonth(String monthFrom, Common cmn) {
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
//			Thread.sleep(100);			
		}
	}
}






















