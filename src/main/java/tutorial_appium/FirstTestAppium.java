package tutorial_appium;
//khởi chạy trang chủ

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import dev.failsafe.internal.util.Assert;
import inheritance.AuthClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FirstTestAppium{

	static AndroidDriver driver;
	static FileTest ft;

	public static void main(String[] args) {
		
		ft = new FileTest();
		ArrayList<KeySearch> keySearchs = ft.ReadSearch();//hàm đọc file
		
		try {
			Bip(keySearchs);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}
	}
	
	public static void Bip(ArrayList<KeySearch> keySearchs)throws MalformedURLException, InterruptedException  {
		driver = Connection.getConnectionMainAndroid();
		Common cmn = new Common(driver);
		homeTest a = new homeTest(driver);
		Thread.sleep(5000);
		WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Khách sạn\"]"));
		signupKhachsan.click();
		Thread.sleep(1000);
		for (int i = 0; i < keySearchs.size();i++) {
			boolean test = false;
				System.out.println(keySearchs.get(i).getKeySearch() + " " + keySearchs.get(i).getExpect());
			if (i > 6) {					
				 test = a.home2(keySearchs.get(i));	
			} 
			else {					
					test = a.home1(keySearchs.get(i));	
			}
			
			System.out.println("result : " + i + " " + test);
			if (test) {
				String result = "Pass";
				ft.write(i + 9, 7, result);				
			} else {
				String result = "Faild";
				ft.write(i + 9, 7, result);
			}
		}	
//		a.home2();
		
//A1		Button Bo qua
		
//		WebElement boqua = driver.findElement(By.id("com.tripadvisor.tripadvisor:id/bdlBtnSkip"));
//		boqua.click();
//		
//		WebElement desau = driver.findElement(By.id("com.tripadvisor.tripadvisor:id/bdlBtnNotNow"));
//		desau.click();
		
//B tu choi sau khi k cho truy cap vi tri		
//		WebElement tuchoitruycap = driver.findElement(ByXPath.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup"));
//		tuchoitruycap.click();
// button cho phep du lieu dia diem
		
//		WebElement RequestLocationPermission = driver.findElement(By.id("com.tripadvisor.tripadvisor:id/btnRequestLocationPermission"));
//		RequestLocationPermission.click();
		
// tu choi truy cap vi tri 
//		WebElement tuchoi1 = driver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button"));
//		tuchoi1.click();
		
//A button de sau sau khi dang ky case dung
		
//		WebElement desau1 = driver.findElement(By.id("com.tripadvisor.tripadvisor:id/bdlBtnNotNow"));
//		desau1.click();

	
		
		System.out.println("Application started ... bip bip bip : ");
//		driver.quit();
		
	}
}


//com.tripadvisor.tripadvisor:id/txtDescription  Vui lòng kiểm tra email và mật khẩu rồi thử lại.

