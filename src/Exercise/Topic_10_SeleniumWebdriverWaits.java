package Exercise;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_10_SeleniumWebdriverWaits {
	WebDriver driver;
	WebDriverWait waitExplicit;
	Date date;

	@Test
	public void TC_01_ImplicitWait() {
		
		//Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//Step 02 - Define an implicit wait (If you set 2 seconds, test will fail)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Step 03 - Click the Start button
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		//Step 04 - Wait result text will appear
		//Step 05 - Check result text is "Hello World!"
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}

	@Test
	public void TC_02_ExplicitWait_visibilityAndPresence() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		//Check visiable. Trước khi thao tác với element thì nên sử dụng wait explicit như 1 cái điều kiện tiên quyết
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		//Check presence ( có trong DOM - Pass)
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='loading']")));
		
		//Check invisble(Check cho nó biến mất đi: 5s nó biến mất rồi -> Đang wait cho nó biến mất tận 30s.)
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}
	@Test
	public void TC_03_ExplicitWait_visibility_Presence() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		//Check visiable. Trước khi thao tác với element thì nên sử dụng wait explicit như 1 cái điều kiện tiên quyết
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		//Check presence ( có trong DOM - Pass)
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='loading']")));
		
		//Check visble(Check nhìn thấy.)
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}
	
	@Test
	public void TC_04_ExplicitWait_DontAppearInDOM() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		System.out.println("------------Start time của check presence---------");
		System.out.println(date=new Date());
		
		//Check presence (Ko có trong DOM -> failed)
		
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		
		System.out.println("------------End time check presence---------");
		System.out.println(date=new Date());
	}
	@Test
	public void TC_05_ExplicitWait_InvisibleInDOMAndNOT() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		//Check invisible (hello word) => ko có trong DOM=> pass -> Chờ hết timeout 10s
		System.out.println("------------Start time check hello word invisible---------");
		System.out.println(date=new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		System.out.println("------------End time check hello word invisible---------");
		System.out.println(date=new Date());
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
		
		//Check invisible (loading) => có trong DOM=> pass -> ko chờ hết timeout -> giây đầu tiên nó đã ko thấy hiển thi trên UI
		System.out.println("------------Start time loading invisible---------");
		System.out.println(date=new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		System.out.println("------------End time loading invisible---------");
		System.out.println(date=new Date());
		
	}
	@Test
	public void TC_06_ExplicitWait_Invisible() {
		//		Check cho loading icon invisible trước khi Helloworld text được hiển thị
		//		Implicit wait chỉ set 2s
		
		//		Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		//			Step 02 - Click the Start button
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		
		//			Step 03 - Wait Loading invisible
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		
		//			Step 04 - Check result text is "Hello World!"
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
		
	}

	@Test
	public void TC_07_ExplicitWait_Visible() {
		//		Check cho Hello world text visible -> sau đó check Helloworld text được hiển thị
		//		Implicit wait chỉ set 2s
		
		//		Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		//		Step 02 - Click the Start button
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		//		Step 03 - Wait Hello World visible
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		
		//		Step 04 - Check result text is "Hello World!"
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
}
	@Test
	public void TC_08_ExplicitWait_Invisible() {
		//		Check cho Helloworld invisible + có trong DOM -> hết bao nhiêu s?
		//				Check cho Helloworld invisible + ko có trong DOM -> hết bao nhiêu s?
		//				Check cho Loading invisible + có trong DOM -> hết bao nhiêu s?
		//				Check cho Loading invisible + ko có trong DOM -> hết bao nhiêu s?
		
		//				Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//				Step 02 - Check cho Helloworld invisible + ko có trong DOM -> hết bao nhiêu s?
		
		System.out.println("------------Start time Hello World invisible---------");
		System.out.println(date=new Date());
		
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		
		System.out.println("------------End time Hello World invisible---------");
		System.out.println(date=new Date());
		
		//				Step 03 -Check cho Loading invisible + có trong DOM -> hết bao nhiêu s?
		System.out.println("\n------------Start time loading invisible---------");
		System.out.println(date=new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		System.out.println("------------And time loading invisible---------");
		System.out.println(date=new Date());
		
		//				Step 04 - Click the Start button
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		//				Step 05 - Check cho Loading invisible + ko có trong DOM -> hết bao nhiêu s?
		System.out.println("\n------------Start time loading invisible---------");
		System.out.println(date=new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		System.out.println("------------End time loading invisible---------");
		System.out.println(date=new Date());
		
	}
	@Test
	public void TC_09_ExplicitWait() {
		
//		Step 01 - Truy cập vào trang:http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
//			Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='RadAjaxPanel inlinePanel']")));
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='RadAjaxPanel inlinePanel']")));
		
//			Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
		System.out.println(driver.findElement(By.xpath("//span[@class='label']")).getText());
		
//			Step 04 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
		driver.findElement(By.xpath("//a[text()='28']")).click();
		
//			Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility ) Xpath: //div[@class='raDiv']
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		
//			Step 06 - Wait cho selected date = 23 được visible ((sử dụng: visibility) Xpath: //*[contains(@class,'rcSelected')]//a[text()='23']
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='28']")));
		
//			Step 07 - Verify ngày đã chọn bằng = Saturday, September 23, 2017
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='label' and text()='Thursday, March 28, 2019']")).getText(), "Thursday, March 28, 2019");
	}
	@Test
	public void TC_10_FluentWait() {
		
//		Step 01 - Truy cập vào trang: https://daominhdam.github.io/fluent-wait/
		driver.get("https://daominhdam.github.io/fluent-wait/");
		
//			Step 02 - Wait cho đến khi countdown time được visible (visibility)
//			Step 03 - Sử dụng Fluent wait để:
//			Mỗi 1s kiểm tra countdount= 00 được xuất hiện trên page hay chưa (giây đếm ngược về 00)
//			Tức là trong vòng 15s (tổng thời gian), cứ mỗi 1 giây verify xem nó đã đếm ngược về giây 00 hay chưa
	}
@BeforeMethod
public void beforMethod() {
	System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	driver= new ChromeDriver();
	waitExplicit=new WebDriverWait(driver, 30);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
}
@AfterMethod
public void afterMethod() {
	//driver.quit();
}
}
