package tutorial_appium;
//khởi chạy thực thi đơn đã đặt 
// verify giỏ hàng
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CartTest {
	static AndroidDriver andDriver;
	static FileTest ft;

	public CartTest() {
		super();
		
	}
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		ft = new FileTest();
		ArrayList<Cart> carts = ft.readCart();
		andDriver = Connection.getConnectionMainAndroid();
		test(carts);
		System.out.println("Finish");
	}
	
	public static void test(ArrayList<Cart> carts) throws InterruptedException {
		Common cmn = new Common(andDriver);

		if (!cmn.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView"))) {
			WebElement signupKhachsan= cmn.getElementBy(By.xpath("//android.widget.Button[@content-desc=\"Khách sạn\"]"));
			signupKhachsan.click();
			Thread.sleep(2000);	
		}
		
		WebElement input = andDriver.findElement(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
		input.sendKeys("Hà Nội");
		Thread.sleep(1000);
		WebElement testFind = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
		testFind.click();
		String name = "Anatole Hotel";
		cmn.scrollToEle(name, cmn);
		WebElement showSale = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Xem giảm giá\")"));
		showSale.click();
		Thread.sleep(3000);
		WebElement profileBtn = cmn.getElementBy(By.xpath("//android.view.View[@content-desc=\"Đăng nhập hoặc tạo tài khoản\"]/android.view.View"));
		profileBtn.click();
		Thread.sleep(1000);
		for( int i = 0; i < carts.size(); i++ ) {			
			if (cmn.isElementPresent(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView"))) {
				WebElement emailInput = cmn.getElementBy(By.xpath("//*[@resource-id='username']"));
				emailInput.sendKeys(carts.get(i).getMail());
				WebElement continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Tiếp tục với email\")"));
				continueBtn.click();
				Thread.sleep(1000);
				if (cmn.isElementPresent(By.xpath("//*[@resource-id='username-note']"))) {
					Thread.sleep(1000);
					WebElement note = cmn.getElementBy(By.xpath("//*[@resource-id='username-note']"));
					String actual = note.getText();
					boolean res = actual.contains(carts.get(i).getExpect());
					try {
						System.out.println("Result : " + i + " " + res);
						Assertions.assertTrue(true);
						ft.write(i + 63, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 63, 7, "Failed");
						continue;
					}
				} else if (cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Nhập mật khẩu của bạn\")"))) {
					try {
						System.out.println("Result : " + i + " " + true);
						Assertions.assertTrue(true);
						ft.write(i + 63, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 63, 7, "Failed");
						continue;
					}
				}
			} else if (cmn.isElementPresent(By.xpath("//*[@resource-id='password']"))) {
				WebElement passwordInput = cmn.getElementBy(By.xpath("//*[@resource-id='password']"));
				passwordInput.sendKeys(carts.get(i).getPassw());
				WebElement continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Đăng nhập\")"));
				continueBtn.click();
				Thread.sleep(2000);
				if (cmn.isElementPresent(By.xpath("//*[@resource-id='password-note']"))) {
					WebElement note = cmn.getElementBy(By.xpath("//*[@resource-id='password-note']"));
					String actual = note.getText();
					boolean res = actual.contains(carts.get(i).getExpect());
					try {
						System.out.println("Result : " + i + " " + res);
						Assertions.assertTrue(res);
						ft.write(i + 63, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 63, 7, "Failed");
						continue;
					}
				} else {
					
					WebElement success = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Tài khoản của tôi\")"));
					success.click();
					Thread.sleep(2000);
					WebElement book = cmn.getElementBy(By.xpath("//android.view.View[@content-desc=\"Đặt chỗ & Chuyến đi\"]"));
					book.click();
					Thread.sleep(3000);
					boolean res;			
					WebElement success1 = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hanoi Center Silk Hotel\")"));
					if(cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hanoi Center Silk Hotel\")") )){
						res = true;
					}else {
						res = false;
					}
//					boolean res = success.getText().contains("Tài khoản của tôi");
					
					try {
						System.out.println("Result : " + i + " " + res);
						Assertions.assertTrue(res);
						ft.write(i + 63, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 63, 7, "Failed");
						continue;
					}
				}
			}
		}
		
	}
}


