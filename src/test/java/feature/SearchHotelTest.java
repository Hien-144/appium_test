package feature;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import core.BaseTest;
import core.Common;
import core.Connection;
import io.appium.java_client.android.AndroidDriver;
import object.KeySearch;
import tutorial_appium.AuthClass;
import tutorial_appium.homeTest;
import ultis.FileTest;

public class SearchHotelTest extends BaseTest{
	AndroidDriver driver;
	ArrayList<KeySearch> keySearchs;
	AuthClass ac;
	FileTest ft;
	Common cmn;
	homeTest ht;
	
    @BeforeTest
    public void beforeTest() throws MalformedURLException, InterruptedException {
        System.out.println("Before test SearchHotelTest");

	    ft = new FileTest();
		keySearchs = ft.ReadSearch();//hàm đọc file

		driver = Connection.getConnectionMainAndroid();
		cmn = new Common(driver);
		ht = new homeTest(driver);
		Thread.sleep(5000);
		WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Khách sạn\"]"));
		signupKhachsan.click();
		Thread.sleep(1000);
    }
    
    @Test
    public void test() throws InterruptedException {
    	for (int i = 0; i < keySearchs.size();i++) {
			boolean test = false;
			if (i <=6) {
				if (i == 6) {					
					test = ht.home2(keySearchs.get(i));	
				} 
				else if(i < 6) {					
					test = ht.home1(keySearchs.get(i));	
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
    
//    @AfterMethod
//	public void tearDownTest() {
//		System.out.println("Quit driver");
//		driver.quit();
//	}

}
