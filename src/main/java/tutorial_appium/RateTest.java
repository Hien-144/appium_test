package tutorial_appium;
//khởi chạy đánh giá 
//verify đánh giá

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v105.fetch.model.AuthChallenge;
import org.opentest4j.AssertionFailedError;

import core.Common;
import core.Connection;
import inheritance.AuthClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import object.Rated;
import object.UserLogin;
import ultis.FileTest;

public class RateTest {
	static AndroidDriver andDriver;
	static FileTest ft;

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		ft = new FileTest();
		ArrayList<Rated> rates = ft.readRated();
		test(rates);
		System.out.println("Finish");
		
		
	}
	

	public static void test (ArrayList<Rated> rates) throws InterruptedException, MalformedURLException {
		andDriver = Connection.getConnectionMainAndroid();
		Common cmn = new Common(andDriver);
		Thread.sleep(3000);
		WebElement tabAccount = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/tab_account"));
		tabAccount.click();
		Thread.sleep(2000);
		if (cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/btnLogIn"))) {
			WebElement btnLogin = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnLogIn"));
			btnLogin.click();
			WebElement mailContinue = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnEmail"));
			mailContinue.click();
			Thread.sleep(1000);
			AuthClass ac = new AuthClass(andDriver);
			boolean res = ac.loginSuccessSuccess(new UserLogin("bipbipbip112@gmail.com", "bipbipbip112@", ""));
			if (!res) {
				System.out.println("Đăng nhập thất bại.");
				return ;
			}
		}
		WebElement tabReview= cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/tab_review"));
		tabReview.click();
		Thread.sleep(1000);
		WebElement btnWriteReview = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnWriteAReview"));
		btnWriteReview.click();
		Thread.sleep(1000);
		WebElement search = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/edtSearchString"));
		search.sendKeys("Ha Long");
		Thread.sleep(2000);
		WebElement elemeRes = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hạ Long\")"));
		elemeRes.click();
		Thread.sleep(1000);

		WebElement continueBtn = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnNext"));
		for (int i = 0; i < rates.size(); i++) {
			
			if (i <= 5) {
				if (i == 0) {
					
					continueBtn.click();
					
					WebElement errorRank = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + rates.get(i).getExpect() + "\")"));
					boolean res = errorRank != null;
					try {
						System.out.println("Result : " + i + " " + res);
						Assertions.assertTrue(res);
						ft.write(i + 81, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 81, 7, "Failed");
						continue;
					}
				} else {
					continueBtn.click();
					
//					System.out.println(Integer.parseInt(rates.get(i).getVote()) + "cc" + i);
					String id = "com.tripadvisor.tripadvisor:id/imgBubble" + i;
					WebElement bubble = cmn.getElementBy(By.id(id));
					bubble.click();
					Thread.sleep(1000);
					WebElement errorRank = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtSelectedRating"));
					boolean res = errorRank.getText().contains(rates.get(i).getExpect());
					Thread.sleep(1000);
					try {
						System.out.println("Result : " + i + " " + res);
						Assertions.assertTrue(res);
						ft.write(i + 81, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 81, 7, "Failed");
						continue;
					}
				}
			} else if (i <= 7) {
				continueBtn.click();
				if (i == 6) {
					WebElement errorRank = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + rates.get(i).getExpect() + "\")"));
					boolean res = errorRank != null;
					try {
						
						System.out.println("Result : " + i + " " + res);
						Assertions.assertTrue(res);
						ft.write(i + 81, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 81, 7, "Failed");
						continue;
					}
				} else {
				
					WebElement friend = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + rates.get(i).getType() + "\")"));
					friend.click();
//					System.out.println(rates.get(i).getType());
					continueBtn.click();

					WebElement next = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hạ Long\")"));
					boolean res = next != null;
					try {
						System.out.println("Result : " + i + " " + res);
						Assertions.assertTrue(res);
						ft.write(i + 81, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 81, 7, "Failed");
						continue;
					}
				}
			} else {
				if (i <= 10) {
					if(i == 8) {
						
						continueBtn.click();
						boolean res = false;
						Thread.sleep(1000);
						if(cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\""+ rates.get(i).getExpect() +"\")"))){
							res = true;						
						}
						try {
							System.out.println("Result : " + i + " " + res);
							Assertions.assertTrue(res);
							ft.write(i + 81, 7, "Pass");
						} catch (AssertionFailedError e) {
							// TODO: handle exception
							ft.write(i + 81, 7, "Failed");
							continue;
						}
						
					}
					Thread.sleep(2000);
					if (!cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/edtInput"))) {
						WebElement rate = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\"Viết đánh giá\")"));
						rate.click();
					}
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
						System.out.println("Result : " + i + " " + res);
						Assertions.assertTrue(res);
						ft.write(i + 81, 7, "Pass");
					} catch (AssertionFailedError e) {
						// TODO: handle exception
						ft.write(i + 81, 7, "Failed");
						if (i < 10) {
//							cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary")).click();
						}
						continue;
					}	
				}
				else {
					Thread.sleep(2000);
					if(i == 11) {
//						return;
						WebElement continueBtn1 = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().text(\"Tiếp\")"));
						continueBtn1.click();
						boolean res = false;
						Thread.sleep(1000);
						if(cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().text(\""+ rates.get(i).getExpect() +"\")"))){
							res = true;						
						}
						WebElement testt = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Vui\")"));
						try {
							System.out.println("Result : " + i + " " + res);
							Assertions.assertTrue(res);
							ft.write(i + 81, 7, "Pass");
						} catch (AssertionFailedError e) {
							// TODO: handle exception
							ft.write(i + 81, 7, "Failed");
							continue;
						}
						
					} else {
						if (!cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/edtInput"))) {
							WebElement rate = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Đặt tiêu đề cho đánh giá này\")"));
							rate.click();
						}
						WebElement editText = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/edtInput"));
	
						editText.sendKeys(rates.get(i).getHeading());
						if (i == 12) {
							boolean res = editText.getText().equalsIgnoreCase(rates.get(i).getExpect());
							try {
								System.out.println("Result : " + i + " " + res);
								Assertions.assertFalse(res);
								ft.write(i + 81, 7, "Pass");
							} catch (Exception e) {
								// TODO: handle exception
								ft.write(i + 81, 7, "Failed");
								continue;
							}
						} else {
							continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Hoàn tất\")"));
							continueBtn.click();
							Thread.sleep(2000);
							if (i == 13) {
								continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Tiếp\")"));
								continueBtn.click();
								Thread.sleep(1000);
								continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Gửi\")"));
								continueBtn.click();
								Thread.sleep(2000);
								continueBtn = cmn.getElementBy(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Chấp nhận\")"));
								continueBtn.click();
								Thread.sleep(2000);
								try {
									WebElement thank = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtTitle"));
									boolean res = thank.getText().contains(rates.get(i).getExpect());

									System.out.println("Result : " + i + " " + res);
									Assertions.assertTrue(res);
									ft.write(i + 81, 7, "Pass");
								} catch (Exception e) {
									// TODO: handle exception
									ft.write(i + 81, 7, "Failed");
									continue;
								}
							} else {
								try {
									boolean res = false;
									if (cmn.isElementPresent(By.id("com.tripadvisor.tripadvisor:id/txtDescription"))) {
										WebElement desc = cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/txtDescription"));
										res = desc.getText().contains(rates.get(i).getExpect());	
										cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary")).click();						
									} else if (i == 12) {
										String actual = cmn.getElementBy(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/androidx.cardview.widget.CardView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]")).getText();
										res = actual.contains(rates.get(i).getExpect());
									}else {
										if (cmn.isElementPresent(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Đặt tiêu đề cho đánh giá này\")"))) {
											res = true;
										}
									}

									System.out.println("Result : " + i + " " + res);
									Assertions.assertTrue(res);
			//						cmn.getElementBy(By.id("com.tripadvisor.tripadvisor:id/btnPrimary")).click();
									ft.write(i + 81, 7, "Pass");
								} catch (AssertionFailedError e) {
									// TODO: handle exception
									ft.write(i + 81, 7, "Failed");
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
			
		}
	}
}

