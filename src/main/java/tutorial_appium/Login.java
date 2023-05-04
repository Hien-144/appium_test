package tutorial_appium;
//khởi chạy login
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
			// TODO: handle exception
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
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

			System.out.print(userLogin.get(i).getUsername() + " " + userLogin.get(i).getPassword() + " "); 
			System.out.println("	+ \" \"Result " + i + "   " + a);
		}
		
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

//	public void loginSuccess(String username, String password){
//		
//	}
}
