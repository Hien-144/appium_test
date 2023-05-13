package tutorial_appium;
//khởi chạy search 
//Verify button search khi chọn tìm kiếm qua nút khách sạn

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
			if (i <=6) {
				if (i == 6) {					
					test = a.home2(keySearchs.get(i));	
				} 
				else if(i < 6) {					
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
		}
		System.out.println("Finish");
	}
}
