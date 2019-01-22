package Exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.WebElement;

public class Topic_03_CheckElement {
	WebDriver driver;
	
	By emailTextbox =By.xpath("//input[@id='mail']");
	By under18Radio =By.xpath("//input[@id='under_18']");
	By educationTextArea =By.xpath("//textarea[@id='edu']");
	
	By job1DropDown =By.xpath("//select[@id='job1']");
	By deverlopmetCheckBox =By.xpath("//input[@id='development']");
	By slider01 =By.xpath("//input[@id='slider-1']");
	By enabledButton =By.xpath("//button[@id='button-enabled']");
	
	By passwordTextbox =By.xpath("//input[@id='password']");
	By AgedisabledRadio =By.xpath("//input[@id='radio-disabled']");
	By bioTextArea =By.xpath("//textarea[@id='bio']");
	By job2DropDown =By.xpath("//select[@id='job2']");
	By checkboxDisabled =By.xpath("//input[@id='check-disbaled']");
	By sliderDisabled =By.xpath("//input[@id='slider-2']");
	By buttonDisabled =By.xpath("//button[@id='button-disabled']");
	
	String Enabled=" ---> Element is enabled";
	String Disabled=" ---> Element is disabled";
  @Test
  public void TC_01_CheckElementDisplayed() {
	  
	  if(isElementDisplayed(emailTextbox)) {
		  driver.findElement(emailTextbox).sendKeys("Automation Testing");
	  }
	  if(isElementDisplayed(under18Radio)) {
		  driver.findElement(under18Radio).click();;
	  }
	  if(isElementDisplayed(educationTextArea)) {
		  driver.findElement(educationTextArea).sendKeys("Automation Testing");
	  }
  }
@Test
	public void TC_02_CheckElementEnableDisable() {
	
	Assert.assertTrue(isElementEnabled(emailTextbox));
	Assert.assertTrue(isElementEnabled(under18Radio));
	Assert.assertTrue(isElementEnabled(educationTextArea));
	Assert.assertTrue(isElementEnabled(job1DropDown));
	Assert.assertTrue(isElementEnabled(deverlopmetCheckBox));
	Assert.assertTrue(isElementEnabled(slider01));
	Assert.assertTrue(isElementEnabled(enabledButton));
	
	Assert.assertFalse(isElementEnabled(passwordTextbox));
	Assert.assertFalse(isElementEnabled(AgedisabledRadio));
	Assert.assertFalse(isElementEnabled(bioTextArea));
	Assert.assertFalse(isElementEnabled(job2DropDown));
	Assert.assertFalse(isElementEnabled(checkboxDisabled));
	Assert.assertFalse(isElementEnabled(sliderDisabled));
	Assert.assertFalse(isElementEnabled(buttonDisabled));
/*
	String TextboxEmail="//*[@id='mail']";
	  if(isElementDisplayed1(driver,TextboxEmail)) {
		  System.out.println("TextboxEmail: "+Enabled);
	  }
	  else {
		  System.out.println("TextboxEmail: "+Disabled);
	  }
	 String Password1=".//*[@id='password']";
	  if(isElementDisplayed1(driver,Password1)) {
		  System.out.println("Password1: "+Disabled);
	  }
	  else {
		  System.out.println("Password1: "+Enabled);
	  }
*/
	}
@Test
	public void TC_03_CheckIsElementSelected() {
	
		driver.findElement(under18Radio).click();
		//driver.findElement(By.xpath(".//*[@id='over_18']")).click();
		Assert.assertTrue(isElementSelected(under18Radio));
		
		driver.findElement(deverlopmetCheckBox).click();
		Assert.assertTrue(isElementSelected(deverlopmetCheckBox));
		
	}
	
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()) {
			System.out.println(by+ "--->Displayed");
			return true;
		}else {
			System.out.println(by+"---> isn't Displayed");
			return false;
		}
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element=driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println(by+Enabled);
			return true;
		}else {
			System.out.println(by+Disabled);
			return false;
		}
	}
	public boolean isElementSelected(By by) {
		
		WebElement element=driver.findElement(by);
		if(element.isSelected()) {
			System.out.println(by+" ---> Selected");
			return element.isSelected();
		}else {
			System.out.println(by+ " ---> isn't Selected");
			element.click();
			return element.isSelected();
		}
	}
/*
	public boolean isElementDisplayed1(WebDriver driver, String yourLocator) {
	  try {
		  WebElement locator=driver.findElement(By.xpath(yourLocator));
		  return locator.isDisplayed();
	  }catch(NoSuchElementException e) {
		  return false;
	  }
	}
	public boolean isElementEnabled1(WebDriver driver, String yourLocator) {
		  try {
			  WebElement locator=driver.findElement(By.xpath(yourLocator));
			  return locator.isEnabled();
		  }catch(NoSuchElementException e) {
			  return false;
		  }
		}
*/
  @BeforeMethod
  public void beforeMethod() {
	  driver=new FirefoxDriver();
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

}
