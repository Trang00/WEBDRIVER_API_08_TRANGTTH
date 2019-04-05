package Selenium_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Button_Radiobutton_Chekcbox_Alert {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor javascript;
	@Test
	public void TC_01_HandleButton() {
			//Step 01 - Truy cập vào trang: http://live.guru99.com/
			//Step 02 - Click vào link My Account dưới footer (Sử dụng Javascript Executor code)
			//Step 03 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/login/
			//Step 04 - Click vào button CREATE AN ACCOUNT (Sử dụng Javascript Executor code)
			//Step 06 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/create/
		driver.get("http://live.guru99.com/");
		
		WebElement myAccountLink=driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		javascript.executeScript("arguments[0].click();",myAccountLink);
		
		String myAccountURL=driver.getCurrentUrl();
		Assert.assertEquals(myAccountURL, "http://live.guru99.com/index.php/customer/account/login/");
		
		WebElement createAnAccountBtn=driver.findElement(By.xpath("//span[text()='Create an Account']"));
		javascript.executeScript("arguments[0].click();",createAnAccountBtn);
		
		String createAnAccountURL=driver.getCurrentUrl();
		Assert.assertEquals(createAnAccountURL, "http://live.guru99.com/index.php/customer/account/create/");
	}
	@Test
	public void TC_02_CheckBox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		WebElement dualZoneLable=driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']"));
		WebElement dualZoneCheckBox=driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		dualZoneLable.click();
		
		Assert.assertTrue(dualZoneCheckBox.isSelected());
		
		if(dualZoneCheckBox.isSelected()) {
			dualZoneLable.click();
		}
		Assert.assertFalse(dualZoneCheckBox.isSelected());
	}
	@Test
	public void TC_02_CheckBoxCustom() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		WebElement dualZoneCheckBox=driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		javascript.executeScript("arguments[0].click();", dualZoneCheckBox);
		
		Assert.assertTrue(dualZoneCheckBox.isSelected());
		
		if(dualZoneCheckBox.isSelected()) {
			javascript.executeScript("arguments[0].click();", dualZoneCheckBox);
		}
		Assert.assertFalse(dualZoneCheckBox.isSelected());
	}
	@Test
	public void TC_03_Radio() {
		
		//Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/radios
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		//Step 02 - Click vào radiobutton:  2.0 Petrol, 147kW (Thẻ input ko được sử dụng thuộc tính id)
		WebElement Petrol2Radio=driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		javascript.executeScript("arguments[0].click();", Petrol2Radio);
		
		Assert.assertTrue(Petrol2Radio.isSelected());
		//Step 03 - Kiểm tra radio button đó đã ch�?n hay chưa/ nếu chưa ch�?n lại
		if(!Petrol2Radio.isSelected()) {
			javascript.executeScript("arguments[0].click();", Petrol2Radio);
		}
	}
	@Test
	public void TC_04_AlertAccept() {
		//tep 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		//Step 02 - Click vào button: Click for JS Alert
		//Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
		//Step 04 - Accept alert và verify message hiển thị tại Result là:  You clicked an alert successfully
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert alert=driver.switchTo().alert();
		String jsAlertMsg=alert.getText();
		Assert.assertEquals(jsAlertMsg, "I am a JS Alert");
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result'and text()='You clicked an alert successfully ']")).isDisplayed());
	}
	@Test
	public void TC_05_AlertCancel() {
		//Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		//Step 02 - Click vào button: Click for JS Alert
		//Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
		//tep 04 - Cancel alert và verify message hiển thị tại Result là:  You clicked: Cancel
		
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert alertCancel=driver.switchTo().alert();
		String jsAlertMsg=alertCancel.getText();
		Assert.assertEquals(jsAlertMsg, "I am a JS Confirm");
		alertCancel.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked: Cancel']")).isDisplayed());
	}
	@Test
	public void TC_06_AlertSenkey() {
		//Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		//Step 02 - Click vào button: Click for JS Prompt
		//Step 03 - Verify message hiển thị trong alert là: I am a JS prompt
		//Step 04 - Nhập vào text bất kì (daominhdam) và verify message hiển thị tại Result là:  You entered: daominhdam

		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		String alertTextInput="DaoMinhDam";
		Alert alertSenkey=driver.switchTo().alert();
		String jsAlertMsg1=alertSenkey.getText();
		Assert.assertEquals(jsAlertMsg1,"I am a JS prompt");
		alertSenkey.sendKeys(alertTextInput);
		alertSenkey.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: "+alertTextInput+"']")).isDisplayed());
	}
	@Test
	public void TC_07_AlertSenkey() {
		driver.get("http://admin::admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	@BeforeMethod
	 public void beforeMethod() {
		driver=new FirefoxDriver();
		wait= new WebDriverWait(driver, 30);
		javascript=(JavascriptExecutor) driver ;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	  public void afterMethod() {
		driver.quit();
	  }
}
