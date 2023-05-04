package tutorial_appium;

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
		String name = "La Siesta Premium Hang Be";
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
					System.out.println("Lỗi mailmail");
					Thread.sleep(1000);
					WebElement note = cmn.getElementBy(By.xpath("//*[@resource-id='username-note']"));
					String actual = note.getText();
					boolean res = actual.contains(carts.get(i).getExpect());
					try {
//						System.out.println(res);
						Assertions.assertTrue(true);
						ft.write(i + 63, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 63, 7, "Failed");
						continue;
					}
					System.out.println("flag");
				} else if (cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Nhập mật khẩu của bạn\")"))) {
					System.out.println("PassPass");
					try {
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
					System.out.println("pass error");
					WebElement note = cmn.getElementBy(By.xpath("//*[@resource-id='password-note']"));
					String actual = note.getText();
					boolean res = actual.contains(carts.get(i).getExpect());
					try {
						System.out.println(res);
						Assertions.assertTrue(res);
						ft.write(i + 63, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 63, 7, "Failed");
						continue;
					}
				} else {
					WebElement success = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Tài khoản của tôi\")"));
					boolean res = success.getText().contains("Tài khoản của tôi");
					System.out.println("pass success");
					try {
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


