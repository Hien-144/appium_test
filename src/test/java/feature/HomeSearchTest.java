package feature;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import core.Common;
import core.Connection;
import io.appium.java_client.android.AndroidDriver;
import object.KeySearch;
import object.UserLogin;
import tutorial_appium.AuthClass;
import tutorial_appium.homeTest;
import ultis.FileTest;

public class HomeSearchTest {
	AndroidDriver driver;
	ArrayList<KeySearch> keySearchs;
	AuthClass ac;
	FileTest ft;
	Common cmn;
	homeTest ht;
	
    @BeforeTest
    public void beforeTest() throws MalformedURLException, InterruptedException {
        System.out.println("Before test");

	    ft = new FileTest();
		keySearchs = ft.ReadSearch();//hàm đọc file

		driver = Connection.getConnectionMainAndroid();
		cmn = new Common(driver);
		ht = new homeTest(driver);
		Thread.sleep(5000);
		WebElement btnSearchHome= cmn.getElementBy(By.xpath("//android.widget.FrameLayout[@content-desc=\"Tìm kiếm\"]/android.widget.ImageView"));
		btnSearchHome.click();
		Thread.sleep(1000);
		WebElement textSearch = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
		textSearch.click();
		Thread.sleep(1000);
    }
    
    @Test
    public void test() throws InterruptedException {
    	for (int i = 0; i < keySearchs.size();i++) {
			boolean test = false;
			if (i > 6){					
				
				if (i > 12) {					
//					System.out.println(keySearchs.get(i).getKeySearch() + " " + keySearchs.get(i).getExpect());
					 test = ht.home2(keySearchs.get(i));	
				} else {
					test = ht.home1(keySearchs.get(i));	
				}
				System.out.println("result : " + (i - 6) + " " + test);
				if (test) {
					String result = "Pass";
					ft.write(i + 66, 7, result);				
				} else {
					String result = "Faild";
					ft.write(i + 66, 7, result);
				}
			}	
		}	
		System.out.println("Finish");
    }
	
	
}
