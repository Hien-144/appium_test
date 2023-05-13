package tutorial_appium;
//khởi chạy login
// Verify đăng nhập bằng email
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import inheritance.AuthClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Login {
	
	static AndroidDriver driver;
	static FileTest ft;
	
	public static void main(String[] args) {
		
		ft = new FileTest();
		ArrayList<UserLogin> userLogin = ft.Read();//hàm đọc file
		
		try {
			Bip(userLogin);
			
		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e.getCause());
//			System.out.println(e.getMessage());
		}
	}
	
	public static void Bip(ArrayList<UserLogin> userLogin)throws MalformedURLException, InterruptedException  {
		driver = Connection.getConnectionLaunch();
		Common cmn = new Common(driver);
		WebElement test = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnEmail"));
		test.click();
		
		AuthClass ac = new AuthClass(driver);
		
		for( int i=0;i<userLogin.size();i++) {
			boolean a = ac.login(userLogin.get(i));
			if (a) {
				String result = "Pass";
				ft.write(i + 1, 7, result);				
			} else {
				String result = "Faild";
				ft.write(i + 1, 7, result);
			}

//		driver.quit();
		
		}
		System.out.println("Finish");
	}
}
