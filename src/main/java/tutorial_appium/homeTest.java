package tutorial_appium;
//thực thi testcase trang chủ
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;

public class homeTest extends Common{
	AndroidDriver driver;

	public homeTest(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean home1(KeySearch keySearch) throws InterruptedException {
		//click Khach san -> Nhap gt ->SS			
		Thread.sleep(1000);
		WebElement signupinput= this.getElementBy(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
		signupinput.sendKeys(keySearch.getKeySearch());
		
		Thread.sleep(2000);
		boolean test = false;
		
		if (this.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ keySearch.getExpect() +"\")"))) {
			test = true;
		} else {
			test = false;
		}
		
		try {
			Assertions.assertTrue(test);
			
			return true;
		} catch (AssertionFailedError e) {
			// TODO: handle exception
			return false;
		}		
	}
	
	public boolean home2(KeySearch keySearch) throws InterruptedException {
//		System.out.println(keySearch.getExpect());
		Thread.sleep(1000);
		WebElement signupinput= driver.findElement(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
		signupinput.sendKeys(keySearch.getKeySearch());
		
		Thread.sleep(2000);
		boolean test = false;
		
		if (this.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Xem tất cả kết quả cho \""+ keySearch.getKeySearch() + "\"\")"))) {
			WebElement clickRes = this.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Xem tất cả kết quả cho \""+ keySearch.getKeySearch() + "\"\")"));
			clickRes.click();
			Thread.sleep(2000);
			if (this.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + keySearch.getExpect() + "\")"))) {
				test = true;
			}else {
				test = false;
			}
		}
		
//		for(int i = 1; i <= 3; i++) {
//			if (this.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[" + i + "]"))) {
//				if (this.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[" + i + "]/android.widget.TextView"))) {
//					WebElement test1 = this.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[" + i + "]/android.widget.TextView"));
//					String actualMessage = test1.getText();
//					if (actualMessage.contains(keySearch.getKeySearch())) {					
//						test1.click();
//						Thread.sleep(5000);
//						String resText = "";
//						WebElement res = null;
//						if (this.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView"))) {
//							res = this.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView"));
//						} else if (this.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/txtGlobalNavTitle"))) {
//							System.out.println("ccc111");
//							res = this.driver.findElement(By.id("com.tripadvisor.tripadvisor:id/txtGlobalNavTitle"));
//						}
//						System.out.println(resText);
//						resText = res.getText();
//						System.out.println(resText + "cccccc");
//						if (resText.contains(keySearch.getExpect())) {
//							test = true;
//						}
//					} else {
//					}
//				} else if (this.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[" + i + "]/android.widget.TextView[1]"))) {
//					WebElement test1 = this.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[" + i + "]/android.widget.TextView[1]"));
//					String actualMessage = test1.getText();
//					System.out.println("cccc1");
//					if (actualMessage.contains(keySearch.getKeySearch())) {						
//						test1.click();
//						Thread.sleep(5000);
//						System.out.println("cccc2");
//						if (this.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView"))) {
//							WebElement res = this.driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView"));
//							String resText = res.getText();
//							System.out.println(resText);
//							if (resText.equalsIgnoreCase(keySearch.getExpect())) {
//								test = true;
//							}
//						}
//					}
//				}
//			}
//		}
		try {
			Assertions.assertTrue(test);
			WebElement  backBtn = this.driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Quay lại\"]/android.widget.ImageView"));
			backBtn.click();
			Thread.sleep(2000);
			//android.view.ViewGroup[@content-desc="Quay lại"]/android.widget.ImageView
			return true;
		} catch (AssertionFailedError e) {
			// TODO: handle exception
			WebElement  backBtn = this.driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Quay lại\"]/android.widget.ImageView"));
			backBtn.click();
			return false;
		}		
	}
	

	

}
// x  android.widget.Button[@content-desc="Khách sạn"]
// id com.tripadvisor.tripadvisor:id/bg
// x /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]
// x loc /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[1]/android.widget.CompoundButton[2]
//x 13/7 /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.GridView/android.widget.FrameLayout[18]/android.widget.TextView
//x 14/7 /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.GridView/android.widget.FrameLayout[19]/android.widget.TextView
// id add com.tripadvisor.tripadvisor:id/btnPrimary
// idloi com.tripadvisor.tripadvisor:id/txtTitle
// x mamayxemgiamgia /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[2]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.Button
// 