package core;
//kết nối đt với úng dụng 
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Connection {
	static AndroidDriver andDriverMain1;
	static AndroidDriver driverLaunch;
	public Connection() {
		System.out.println("getConnection");
	}

	public static WebDriver getConnectionMain () throws MalformedURLException, InterruptedException {
//		if (andDriverMain1 != null) {
//			return andDriverMain1;
//		}
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		DesiredCapabilities cc = getDesiredCapabilities("com.tripadvisor.android.ui.primarynavcontainer.MainActivity");
		andDriverMain1 = new AndroidDriver(url, cc);
		andDriverMain1.wait(5000);
		return andDriverMain1;
	}
	
	public static AndroidDriver getConnectionMainAndroid () throws MalformedURLException {
//		if (andDriverMain1 != null) {
//			return andDriverMain1;
//		}
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		DesiredCapabilities cc = getDesiredCapabilities("com.tripadvisor.android.ui.primarynavcontainer.MainActivity");
		andDriverMain1 = new AndroidDriver(url, cc);
//		andDriverMain.manage().wait(5000).timeouts();
		return andDriverMain1;
	}

	public static AndroidDriver getConnectionLaunch () throws MalformedURLException {
//		if (driverLaunch != null) {
//			return driverLaunch;
//		}  
		DesiredCapabilities cc = getDesiredCapabilities("com.tripadvisor.android.ui.launcher.LauncherActivity");
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driverLaunch = new AndroidDriver(url, cc);
		
		return driverLaunch;
	}
	
	public static DesiredCapabilities getDesiredCapabilities(String activity) {
		DesiredCapabilities cc = new DesiredCapabilities();
		cc.setCapability("automationName", "UiAutomator2");
		cc.setCapability("deviceName", "First");
		cc.setCapability("uuid", "2240aeba"); // 16eec1ec  9xifqwskj7tcxklf
		cc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//		cc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
//		cc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
		cc.setCapability("unicodeKeyboard", "true");                                     
		cc.setCapability("resetKeyboard", "true");
		cc.setCapability("appPackage", "com.tripadvisor.tripadvisor");
		cc.setCapability("appActivity", activity);
		
		return cc;
	}
}
