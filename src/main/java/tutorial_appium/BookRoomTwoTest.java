package tutorial_appium;
//thuc thithi

import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;

public class BookRoomTwoTest {
	WebDriver driver;
	AndroidDriver andDriver;
	FileTest ft = new FileTest();
	
	public BookRoomTwoTest(AndroidDriver andDriver) {
//		super(driver);
		this.andDriver = andDriver;
	}
	
}
