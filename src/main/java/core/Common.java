package core;
// class sử dụng chung
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class Common {
	AndroidDriver andDriver;
	
	public Common(AndroidDriver andDriver) {
		super();
		this.andDriver = andDriver;
	}


	public boolean isElementPresent(By by) {   
		try {
			andDriver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;

		}
	}

	public boolean isElementPresentAndroidAndroid(By by) {   
		try {
			andDriver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;

		}
	}

	public WebElement getElementBy(By by) {
		WebElement element;
		while (!this.isElementPresent(by)) {
			
		}
		element = andDriver.findElement(by);
		return element;
	}

	public List<WebElement> getElementsBy(By by) {
		List<WebElement> elements;
		while (!this.isElementPresent(by)) {
			
		}
		elements = andDriver.findElements(by);
		return elements;
	}
	
	public void scrollToEle(String name, Common cmn) {
		while(!cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ name +"\")"))) {
//			WebElement end = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/weekDays"));
			int centerX = 500;
			double startY = 2000;
			double endY = 1100;
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence sequence = new Sequence(finger, 1);
			sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, (int) startY));
			sequence.addAction(finger.createPointerDown(0));
			sequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, (int)endY));
			sequence.addAction(finger.createPointerUp(0));
			andDriver.perform(Arrays.asList(sequence));
		}
		
		WebElement res = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ name +"\")"));
		int centerX = res.getRect().x + (res.getSize().width / 2);
		double startY = res.getRect().y;
		double endY = andDriver.manage().window().getSize().getHeight() / 2; PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence sequence = new Sequence(finger, 1); sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, (int) startY));				
		sequence.addAction(finger.createPointerDown(0));
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, (int)endY));
		sequence.addAction(finger.createPointerUp(0));
		andDriver.perform(Arrays.asList(sequence));
	}

	public void scrollBack(String month, Common cmn) {
		while (!cmn.isElementPresentAndroidAndroid(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"tháng " + month + "\")"))) {
					andDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollBackward(15)"));
				}
	}	

	public void scrollToMonth(String monthFrom, AndroidDriver andDriver) {
		while (!this.isElementPresentAndroidAndroid(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"tháng " + monthFrom + "\")")) 
			&& !this.isElementPresentAndroidAndroid(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"tháng " + (Integer.parseInt(monthFrom) + 1) + "\")"))) {
				andDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollForward(24)"));
			}
		WebElement month = this.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"tháng " + monthFrom + "\")"));
		if (month != null) {
			WebElement end = this.getElementBy(By.id("com.tripadvisor.tripadvisor:id/weekDays"));
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

	public void scrollToEle(String name, AndroidDriver andDriver) {
		while(!this.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ name +"\")"))) {
//			WebElement end = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/weekDays"));
			int centerX = 500;
			double startY = 2000;
			double endY = 1100;
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence sequence = new Sequence(finger, 1);
			sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, (int) startY));
			sequence.addAction(finger.createPointerDown(0));
			sequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, (int)endY));
			sequence.addAction(finger.createPointerUp(0));
			andDriver.perform(Arrays.asList(sequence));
		}
		
		WebElement res = this.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ name +"\")"));
		int centerX = res.getRect().x + (res.getSize().width / 2);
		double startY = res.getRect().y;
		double endY = andDriver.manage().window().getSize().getHeight() / 2; PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence sequence = new Sequence(finger, 1); sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, (int) startY));				
		sequence.addAction(finger.createPointerDown(0));
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, (int)endY));
		sequence.addAction(finger.createPointerUp(0));
		andDriver.perform(Arrays.asList(sequence));
	}
	public void scrollToEleText(String name, AndroidDriver andDriver) {
		while(!this.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().text(\""+ name +"\")"))) {
//			WebElement end = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/weekDays"));
			int centerX = 500;
			double startY = 2000;
			double endY = 1100;
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence sequence = new Sequence(finger, 1);
			sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, (int) startY));
			sequence.addAction(finger.createPointerDown(0));
			sequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, (int)endY));
			sequence.addAction(finger.createPointerUp(0));
			andDriver.perform(Arrays.asList(sequence));
		}
		WebElement res = this.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ name +"\")"));
		int centerX = res.getRect().x + (res.getSize().width / 2);
		double startY = res.getRect().y;
		double endY = andDriver.manage().window().getSize().getHeight() / 2; PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence sequence = new Sequence(finger, 1); sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, (int) startY));				
		sequence.addAction(finger.createPointerDown(0));
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, (int)endY));
		sequence.addAction(finger.createPointerUp(0));
		andDriver.perform(Arrays.asList(sequence));
	}
	
}
