package tutorial_appium;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.opentest4j.AssertionFailedError;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AuthClass extends Common{
	AndroidDriver driver;
	
	public AuthClass(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void test () {
		
	}
	
	public boolean login(UserLogin userLogin) throws InterruptedException {
//		Class<?> callerClass = getClass();
//		WebElement signupBtn = driver.findElement(By.id("com.tripadvisor.tripadvisor:id/btnSignUp"));
//		signupBtn.click();
		
		WebElement emailInput = this.getElementBy(ByXPath.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.EditText"));
		WebElement passInput = this.getElementBy(ByXPath.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.EditText"));
		emailInput.clear();
		passInput.clear();
		emailInput.sendKeys(userLogin.getUsername());
		passInput.sendKeys(userLogin.getPassword());
		
		WebElement signupBtn1 = this.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnSignIn"));
		signupBtn1.click();
		Thread.sleep(2000);
		if (this.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/txtErrorText"))) {
			WebElement txtError = this.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtErrorText"));
			String actualMessage = txtError.getText();
			
			String expectedResult = userLogin.getExpect();
			boolean testB = expectedResult.equalsIgnoreCase(actualMessage);
//			System.out.print(actualMessage + " " + userLogin.getExpect());
			try {
				Assertions.assertTrue(testB);
				return true;
			} catch (AssertionFailedError e) {
//				e.printStackTrace();
				return false;
			}
		} else if (this.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/txtDescription"))) {
			WebElement description = this.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtDescription"));
			
			String actualResult = description.getText();
			String expectedResult1 = userLogin.getExpect();
			Boolean testB1 = expectedResult1.equalsIgnoreCase(actualResult);
			try {
				Assertions.assertTrue(testB1);
				WebElement skip = this.driver.findElement(By.id("com.tripadvisor.tripadvisor:id/bdlBtnTextPrimary"));
				skip.click();
				Thread.sleep(3000);
				
				return testB1;
			} catch (AssertionFailedError e) {
//				e.printStackTrace();
				
				return false;
			}
		} else {
			WebElement txtSuccess = this.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtRequestLocationTitle"));
			String actualResult2 = txtSuccess.getText();
			String expectedResult2 = userLogin.getExpect();
			Boolean testB2 = expectedResult2.equalsIgnoreCase(actualResult2);
			try {
				Assertions.assertTrue(testB2); 
				WebElement after = driver.findElement(By.id("com.tripadvisor.tripadvisor:id/bdlBtnNotNow"));
				after.click();
				Thread.sleep(1000);
			
				return testB2;
			} catch (AssertionFailedError e) {
//				e.printStackTrace();
				
				return false;
			}
		}	
//		return false;
	}
//	com.tripadvisor.tripadvisor:id/txtTitle
//	com.tripadvisor.tripadvisor:id/txtDescription
//	com.tripadvisor.tripadvisor:id/bdlBtnTextPrimary
//	com.tripadvisor.tripadvisor:id/txtRequestLocationTitle
//	com.tripadvisor.tripadvisor:id/txtTitle
//	Hãy tìm hiểu thông tin cơ bản để chúng tôi có thể cung cấp dịch vụ cho bạn.
//	com.tripadvisor.tripadvisor:id/bdlBtnTextPrimary
//	com.tripadvisor.tripadvisor:id/txtErrorText
	public void register() {
		this.driver.findElement(By.id(""));
		
	}
	
	public boolean loginSuccessSuccess(UserLogin userLogin) throws InterruptedException {
//		WebElement signupBtn = driver.findElement(By.id("com.tripadvisor.tripadvisor:id/btnSignUp"));
//		signupBtn.click();
		
		WebElement emailInput = this.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.EditText"));
		WebElement passInput = this.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.EditText"));
		emailInput.clear();
		passInput.clear();
		emailInput.sendKeys(userLogin.getUsername());
		passInput.sendKeys(userLogin.getPassword());
		
		WebElement signupBtn1 = this.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnSignIn"));
		signupBtn1.click();
		Thread.sleep(3000);
		if (this.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().text(\"Đăng xuất\")"))) {			
			return true;
		}
		return false;
	}
}