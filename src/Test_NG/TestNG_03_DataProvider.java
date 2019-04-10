package Test_NG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class TestNG_03_DataProvider {
	WebDriver driver;
	
	
  @Test(dataProvider = "UserAndPass")
  public void TC_01(String email, String pass) {
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
	  driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(email);
	  driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(pass);
	  driver.findElement(By.xpath(".//*[@id='send2']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	 // Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'tranghc0938@gmail.com')]")).isDisplayed());
	 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+email+"')]")).isDisplayed());
	 
	 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	 driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	 
	 Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	 
	
	 
	  
  }
	
  
  
  
  @BeforeMethod
  public void beforeMethod() {
	  driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/index.php");
		
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }


  @DataProvider
  public Object[][] UserAndPass() {
    return new Object[][] {
    	{"tranghc0938@gmail.com","123456"},
    	{"tranghc09381@gmail.com","123456"},
    	{"tranghc09382@gmail.com","123456"}
    };
  }
}
