package feature;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.BaseTest;
import core.Common;
import core.Connection;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Epic;
import object.UserLogin;
import tutorial_appium.AuthClass;
import tutorial_appium.Login;
import ultis.FileTest;


public class LoginTest extends BaseTest {
	AndroidDriver driver;
	ArrayList<UserLogin> userLogin;
	AuthClass ac;
	FileTest ft;
	
    @BeforeTest
    public void beforeTest() throws MalformedURLException {
        System.out.println("Before test LoginTest");

	    ft = new FileTest();
		userLogin = ft.Read();//hàm đọc file
		driver = Connection.getConnectionLaunch();
		Common cmn = new Common(driver);
		WebElement test = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnEmail"));
		test.click();
		
		ac = new AuthClass(driver);
    }

    @Test()
    public void testLogin() throws MalformedURLException, InterruptedException {
        System.out.println("Test");
    	for (UserLogin userLogin2 : userLogin) {
			System.out.println(userLogin2.getUsername());
		}
    	Login lg = new Login();
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
    	}
    }
    
//    @AfterMethod
//	public void tearDownTest() {
//		System.out.println("Quit driver");
//		driver.quit();
//	}
//    
}
