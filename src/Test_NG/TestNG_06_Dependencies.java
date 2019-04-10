package Test_NG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_06_Dependencies {
WebDriver driver;
	
	@Parameters({"email","pass"})
	@Test()
	public void TC_01_(String email, String pass) {// chỉ chạy trên file .xml
		  
		  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		  
		  driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(email);
		  driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(pass);
		  driver.findElement(By.xpath(".//*[@id='send2']")).click();
		  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		 // Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'tranghc0938@gmail.com')]")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+email+"')]")).isDisplayed());
		 
		 
	  }
	@Test(dependsOnMethods="TC_01_")
	public void TC_02() {// chỉ chạy trên file .xml
		  
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		 
		 Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	  }
	  @Parameters("browser")
	  @BeforeClass
	  public void Setupbrowser(String browserName) {
		  System.out.println("Browser name+"+browserName);
		  
		  if(browserName.equals("chrome")) {
			  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			  driver= new ChromeDriver();
		  }else if(browserName.equals("firefox")) {
			  driver=new FirefoxDriver();
				//System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
			  //driver= new FirefoxDriver();
		  }else if(browserName.equals("ie")) {
			  System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			  driver= new InternetExplorerDriver();
		  }else if(browserName.equals("chromeheadless")) {
			  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			  ChromeOptions chromeOptions= new ChromeOptions();
			  chromeOptions.addArguments("--headless");
			  driver= new ChromeDriver(chromeOptions);
		  }
		  
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("http://live.guru99.com/index.php");
			
	  }

	  @AfterClass
	  public void afterMethod() {
		  driver.quit();
	  }

}
