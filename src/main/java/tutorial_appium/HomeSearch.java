package tutorial_appium;
//khởi chạy search 
//Verify button search khi chọn tìm kiếm qua thanh 

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import core.Common;
import core.Connection;
import io.appium.java_client.android.AndroidDriver;
import object.KeySearch;
import ultis.FileTest;

public class HomeSearch {
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
	@Test
	public static void Bip(ArrayList<KeySearch> keySearchs)throws MalformedURLException, InterruptedException  {
		driver = Connection.getConnectionMainAndroid();
		Common cmn = new Common(driver);
		homeTest a = new homeTest(driver);
		Thread.sleep(5000);
		WebElement btnSearchHome= cmn.getElementBy(By.xpath("//android.widget.FrameLayout[@content-desc=\"Tìm kiếm\"]/android.widget.ImageView"));
		btnSearchHome.click();
		Thread.sleep(1000);
		WebElement textSearch = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
		textSearch.click();
		Thread.sleep(1000);
//		
//		System.out.println(keySearchs.size());
		for (int i = 0; i < keySearchs.size();i++) {
			boolean test = false;
			if (i > 6){					
				
				if (i > 12) {					
//					System.out.println(keySearchs.get(i).getKeySearch() + " " + keySearchs.get(i).getExpect());
					 test = a.home2(keySearchs.get(i));	
				} else {
					test = a.home1(keySearchs.get(i));	
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
