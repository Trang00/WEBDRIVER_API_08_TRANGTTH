package Selenium_API;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_08_JAVASCRIPT_EXECUTOR {
	WebDriver driver;
	JavascriptExecutor javascript;
	@Test
	public void TC_01_JavascriptExcecutor_JE() {
		driver.get("http://live.guru99.com/");

		//Step 02 - Sử dụng JE để get domain của page
		
		String domainName = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainName, "live.guru99.com");
	
		//Step 03 - Sử dụng JE để get URL của page
		
		String homepageURL = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(homepageURL, "http://live.guru99.com/");
	
		//Step 04 - Open MOBILE page (Sử dụng JE)
			
		clickToElementByJS("//a[text()='Mobile']");
		
		//Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
		clickToElementByJS("//a[@title='Samsung Galaxy']/following-sibling::div//div[@class='actions']/button[@class='button btn-cart']");
		
		//Step 06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
		String shoppingCartInnerText=(String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(shoppingCartInnerText.contains("Samsung Galaxy was added to your shopping cart."));
		
		//Step 07 - Open PRIVACY POLICY page (Sử dụng JE)
		clickToElementByJS("//a[text()='Privacy Policy']");
		
		//Verify title của page = Privacy Policy (Sử dụng JE)
		String PrivacyTitle=(String) executeForBrowser("return document.title");
		Assert.assertEquals(PrivacyTitle, "Privacy Policy");
		
		//Step 08 - Srcoll xuống cuối page
		scrollToBottomPage();
		
		//Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath
		WebElement Verify=driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertTrue(Verify.isDisplayed());
		
		//	Step 10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE)
		navigateToUrlByJS("http://demo.guru99.com/v4/ ");
		String domainDemoName = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainDemoName, "demo.guru99.com");
	}
	
	@Test
	public void TC_02_RemoveAttibute() {
		
		
		String firstName="Automation Fistname";
		String lastName="Automation Lastname";
		//tep 01 - Truy cập vào trang: https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		
		//Step 02 - Remove thuộc tính disabled của field Last name
		WebElement resultIframe=driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(resultIframe);
		removeAttributeInDOM("//input[@name='lname']", "disabled");
		
		//Step 03 - Sendkey vào field Last name
		sendkeyToElementByJS("//input[@name='fname']", firstName);
		sendkeyToElementByJS("//input[@name='lname']", lastName);
		
		//Step 04 - Click Submit button
		clickToElementByJS("//input[@value='Submit']");
	
		//Step 05 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field Lastname (Automation Testing)
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'"+firstName+"') and contains(text(),'"+lastName+"')]")).isDisplayed());
	}
	@Test
	public void TC_03_CreateAnAccountJS() throws Exception {
		
		String FirstName ="FirstName";
		String LastName="LastName";
		String Password="123456";
		String ConfirmPassword="123456";
		
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		highlightElement("//div[@class='footer']//a[text()='My Account']");
		clickToElementByJS("//div[@class='footer']//a[text()='My Account']");
		
		//Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		highlightElement("//span[text()='Create an Account']");
		clickToElementByJS("//span[text()='Create an Account']");
		
		//Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password 
		sendkeyToElementByJS("//input[@id='firstname']", FirstName);
		sendkeyToElementByJS("//input[@id='lastname']", LastName);
		sendkeyToElementByJS("//input[@id='email_address']","Automationtest"+randomNumber()+"@gmail.com");
		sendkeyToElementByJS("//input[@id='password']", Password);
		sendkeyToElementByJS("//input[@id='confirmation']", ConfirmPassword);
	
		//Step 05 - Click REGISTER button 
		clickToElementByJS("//button[@title='Register']");
		
		//Step 05 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store. 
		//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		String myDashboardInnerText= (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(myDashboardInnerText.contains("Thank you for registering with Main Website Store."));
		
		//Step 06 - Logout kh�?i hệ thống
		clickToElementByJS("//header[@id='header']//span[text()='Account']");
		clickToElementByJS("//a[text()='Log Out']");
		
				//Step 07 - Kiểm tra hệ thống navigate v�? Home page sau khi logout thành công 
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}
	@Test
	public void TC_03_CreateAnAccountBuildIn() throws Exception {
		
		String FirstName ="FirstName";
		String LastName="LastName";
		String Password="123456";
		String ConfirmPassword="123456";
		
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
				
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("Automationtest"+randomNumber()+"@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(ConfirmPassword);
			
		driver.findElement(By.xpath("//button[@title='Register']")).click();
				 
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
				
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
				
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}
	
	public int randomNumber() {
		Random random= new Random();
		int number=random.nextInt(9999);
		return number;
	}
	
	public void highlightElement(String xpathName) {
		WebElement element=driver.findElement(By.xpath(xpathName));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
    }

    public Object executeForBrowser(String javaSript) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javaSript);
    }

    public Object clickToElementByJS(String xpathName) {
    	WebElement element=driver.findElement(By.xpath(xpathName));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].click();", element);
    }

    public Object sendkeyToElementByJS(String xpathName, String value) {
    	WebElement element=driver.findElement(By.xpath(xpathName));
           JavascriptExecutor js = (JavascriptExecutor) driver;
           return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public Object removeAttributeInDOM(String xpathName, String attribute) {
    	WebElement element=driver.findElement(By.xpath(xpathName));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
    }

    public Object scrollToBottomPage() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public Object navigateToUrlByJS(String url) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.location = '" + url + "'");
    }
	
	@BeforeMethod
	public void beforeMethod() {
		driver=new FirefoxDriver();
		javascript=(JavascriptExecutor) driver ;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
