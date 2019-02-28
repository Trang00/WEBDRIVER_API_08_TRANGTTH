package Exercise;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_07_Iframe_Frame {
	WebDriver driver;
	JavascriptExecutor javascript;
	
@Test
public void TC_01() {//OK
	/*	Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
		Step 02 - Close popup nếu có hiển thi
		Step 03 - Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
		Step 04: Verify banner có đúng 6 images (switch qua iframe nếu có
		Step 05 - Verify flipper banner được hiển thị và có 8 items
	 * */
	driver.get("https://www.hdfcbank.com/");
/*	Single -> nếu nó ko hiển thị popup thì sẽ đánh fail TC=>  List xl.
	WebElement notificationIframe=driver.findElement(By.xpath("//div[@id='vizury-notification-container']/iframe"));
	
	//driver.switchTo().frame(4);// bat dau tu 0 , nam o iframe so 5: (//iframe)[5] => index co the thay doi vi tri ko stable
	//driver.switchTo().frame("vizury-notification-template"); // chi nhan id/ class/ name duy nhat. => co TH ko phai la duy nhat
	
	driver.switchTo().frame(notificationIframe); // xly dc ca 2 TH tren
	driver.findElement(By.xpath("//div[@id='div-close']")).click();
*/
	List <WebElement> notificationsIframe =driver.findElements(By.xpath("//div[@id='vizury-notification-container']/iframe"));
	int sizeIframe=notificationsIframe.size();
	System.out.println("SizeIframe:"+sizeIframe);
	if(notificationsIframe.size()>0) {
		Assert.assertTrue(notificationsIframe.get(0).isDisplayed());
		driver.switchTo().frame(notificationsIframe.get(0));
		javascript.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='div-close']")));
		driver.switchTo().defaultContent();// chính là top Window (parent)
	}
	WebElement lookingForIframe=driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	driver.switchTo().frame(lookingForIframe);
	
	WebElement lookingForText=driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']"));
	Assert.assertTrue(lookingForText.isDisplayed());
	driver.switchTo().defaultContent();
	
	//Step 04: Verify banner có đúng 6 images (switch qua iframe nếu có
	WebElement slidingBannerIframe=driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	driver.switchTo().frame(slidingBannerIframe);
	
	List <WebElement> slidingBannerImages=driver.findElements(By.xpath("//div[@class='bannerimage-container']//img"));
	Assert.assertEquals(slidingBannerImages.size(), 6);
	driver.switchTo().defaultContent();
	
	//Step 05 - Verify flipper banner được hiển thị và có 8 items
	List <WebElement> banerImages=driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	System.out.println("Size Img:"+banerImages.size());
	Assert.assertEquals(banerImages.size(), 8);
	// cách 1 dùng for thường
	/*
	for(int i=0; i< banerImages.size();i++) {
		System.out.println("i:"+i);
		Assert.assertTrue(banerImages.get(i).isDisplayed());
	}
	*/
	// cách 2 dùng For- each
	for(WebElement bannerI:banerImages) {
		Assert.assertTrue(bannerI.isDisplayed());
	}
}
@BeforeMethod
public void beforeMethod() {
	driver= new FirefoxDriver();
	javascript=(JavascriptExecutor) driver ;
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
}
@AfterMethod
public void afterMethod() {
	driver.quit();
}
}
