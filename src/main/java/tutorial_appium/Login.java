package tutorial_appium;
//khởi chạy login
// Verify đăng nhập bằng email
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import core.Common;
import core.Connection;
import io.appium.java_client.android.AndroidDriver;
import object.UserLogin;
import ultis.FileTest;

public class Login {
	
	static AndroidDriver driver;
	static FileTest ft;
	public Login() {
	}	
	public static void main(String[] args) {

	    ft = new FileTest();
		ArrayList<UserLogin> userLogin = ft.Read();//hàm đọc file
		
		try {
//			Bip(userLogin);
			Thread.currentThread().getStackTrace();

//			driver = Connection.getConnectionLaunch();
//			AuthClass auth = new AuthClass(driver);
			
//			auth.test();
		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e.getCause());
//			System.out.println(e.getMessage());
		}
	}
	
	public void Bip(ArrayList<UserLogin> userLogin)throws MalformedURLException, InterruptedException  {
		driver = Connection.getConnectionLaunch();
		Common cmn = new Common(driver);
		WebElement test = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnEmail"));
		test.click();
		
		AuthClass ac = new AuthClass(driver);
		
		for( int i=0;i<userLogin.size();i++) {
			boolean a = ac.login(userLogin.get(i));
			System.out.println("Result : " + i + " " + a);
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
