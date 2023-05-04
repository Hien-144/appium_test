package tutorial_appium;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class RateTest {
	static AndroidDriver andDriver;
	static FileTest ft;

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		ft = new FileTest();
		ArrayList<Rated> rates = ft.readRated();
		test(rates);
		
		
	}
	public static void test (ArrayList<Rated> rates) throws InterruptedException, MalformedURLException {
		andDriver = Connection.getConnectionMainAndroid();
		Common cmn = new Common(andDriver);
		Thread.sleep(3000);
		WebElement tabReview= cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/tab_review"));
		tabReview.click();
		Thread.sleep(1000);
		WebElement btnWriteReview = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnWriteAReview"));
		btnWriteReview.click();
		Thread.sleep(1000);
		WebElement search = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
		search.sendKeys("Hà Nội");
		Thread.sleep(1000);
//		System.out.println("ccc");
		WebElement elemeRes = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Phố Cổ Hà Nội\")"));
//		System.out.println(elemeRes.getText());
		elemeRes.click();
		Thread.sleep(1000);
		WebElement continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Tiếp\")"));
		for (int i = 0; i < rates.size(); i++) {
			if (i <= 5) {
				if (i == 0) {
					continueBtn.click();
					
					System.out.println(rates.get(i).getExpect());
					WebElement errorRank = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + rates.get(i).getExpect() + "\")"));
					boolean res = errorRank != null;
					try {
						Assertions.assertTrue(res);
						ft.write(i + 80, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 80, 7, "Failed");
						continue;
					}
				} else {
//					System.out.println(Integer.parseInt(rates.get(i).getVote()) + "cc" + i);
					String id = "com.tripadvisor.tripadvisor:id/imgBubble" + i;
					WebElement bubble = cmn.getElementBy(By.id(id));
					bubble.click();
					Thread.sleep(1000);
					WebElement errorRank = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtSelectedRating"));
					System.out.println(errorRank.getText());
					boolean res = errorRank.getText().contains(rates.get(i).getExpect());
					Thread.sleep(1000);
					try {
						Assertions.assertTrue(res);
						ft.write(i + 80, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 80, 7, "Failed");
						continue;
					}
				}
			} else if (i <= 7) {
				continueBtn.click();
				if (i == 6) {
					WebElement errorRank = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + rates.get(i).getExpect() + "\")"));
					boolean res = errorRank != null;
					System.out.println(res);
					try {
						Assertions.assertTrue(res);
						ft.write(i + 80, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 80, 7, "Failed");
						continue;
					}
				} else {
					System.out.println(rates.get(i).getType());
					WebElement friend = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + rates.get(i).getType() + "\")"));
					friend.click();
					System.out.println(rates.get(i).getType());
					continueBtn.click();

					WebElement next = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Phố Cổ Hà Nội\")"));
					boolean res = next != null;
					System.out.println(res);
					try {
						Assertions.assertTrue(res);
						ft.write(i + 80, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 80, 7, "Failed");
						continue;
					}
				}
			} else {
				System.out.println("rate" + i);
				if (i <= 10) {
					Thread.sleep(2000);
					if (!cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/edtInput"))) {
						System.out.println("cccc");
						System.out.println("");
						WebElement rate = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\"Viết đánh giá\")"));
						rate.click();
					}
					System.out.println(rates.get(i).getRated());
					WebElement editText = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/edtInput"));
					editText.sendKeys(rates.get(i).getRated());
					continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hoàn tất\")"));
					continueBtn.click();
					Thread.sleep(2000);
					try {
						boolean res = false;
						if (i < 10) {
							WebElement desc = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtDescription"));
							res = desc.getText().contains(rates.get(i).getExpect());
							cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary")).click();
						} else if (cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().text(\"Viết đánh giá\")"))) {
							res = true;
						}
						Assertions.assertTrue(res);
						System.out.println("tiếp tực nhậpnhập");
						ft.write(i + 80, 7, "Pass");
						System.out.println();
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 80, 7, "Failed");
						if (i < 10) {
//							cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary")).click();
						}
						continue;
					}	
				} else {
					Thread.sleep(2000);
					System.out.println("title " + rates.get(i).getHeading() + "cc");
					if (!cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/edtInput"))) {
						WebElement rate = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Đặt tiêu đề cho đánh giá này\")"));
						rate.click();
					}
					WebElement editText = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/edtInput"));

					editText.sendKeys(rates.get(i).getHeading());
					continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hoàn tất\")"));
					continueBtn.click();
					Thread.sleep(2000);
					try {
						boolean res = false;
						if (cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/txtDescription"))) {
							WebElement desc = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtDescription"));
							System.out.println("CCCC");
							res = desc.getText().contains(rates.get(i).getExpect());	
							cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary")).click();						
						} else if (i == 12) {
							String actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]")).getText();
							System.out.println(actual);
							res = actual.contains(rates.get(i).getExpect());
						}else {
							if (cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Đặt tiêu đề cho đánh giá này\")"))) {
								res = true;
							}
						}
						Assertions.assertTrue(res);
//						cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary")).click();
						ft.write(i + 80, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 80, 7, "Failed");
						if (i == 11) {
//							cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary")).click();
						}
						continue;
					}	
				}
			}
			
		}
	}
}

//com.tripadvisor.tripadvisor:id/btnWriteAReview
//com.tripadvisor.tripadvisor:id/tab_review
//com.tripadvisor.tripadvisor:id/edtSearchString
//com.tripadvisor.tripadvisor:id/edtInput
//com.tripadvisor.tripadvisor:id/txtField //com.tripadvisor.tripadvisor:id/edtInput
//com.tripadvisor.tripadvisor:id/btnPrimary
//com.tripadvisor.tripadvisor:id/btnSecondary


//Bạn đã xếp hạng trải nghiệm của mình là 5 trên 5. Hãy chia sẻ thêm với chúng tôi.
//Tóm tắt ngắn gọn về chuyến đi của bạn
