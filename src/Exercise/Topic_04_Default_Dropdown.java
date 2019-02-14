package Exercise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Default_Dropdown {
	WebDriver driver;
	By Job1=By.xpath("//select[@id='job1']");
	@Test
	public void TC_01_HTML_DropdownList() {
		
		driver.get(" https://daominhdam.github.io/basic-form/index.html");
	
		//WebElement Job1Select1=driver.findElement(Job1);
		//Select Job1Se=new Select(Job1Select1);
		
	//Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
		Select Job1Select=new Select(driver.findElement(Job1));
		Assert.assertFalse(Job1Select.isMultiple());
		
	//Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		Job1Select.selectByVisibleText("Automation Tester");
		
	//Step 04 - Kiểm tra giá trị đã được chọn thành công
		String actualJob1SelectText=Job1Select.getFirstSelectedOption().getText();
		Assert.assertEquals("Automation Tester", actualJob1SelectText);
		
//		Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
		Job1Select.selectByValue("manual");
		
//		Step 06 - Kiểm tra giá trị đã được chọn thành công
		String actualJob1SelectValue=Job1Select.getFirstSelectedOption().getText();
		Assert.assertEquals("Manual Tester", actualJob1SelectValue);
		
//		Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
//		Step 08 - Kiểm tra giá trị đã được chọn thành công
		Job1Select.selectByIndex(3);
		String actualJob1SelectIndex=Job1Select.getFirstSelectedOption().getText();
		Assert.assertEquals("Mobile Tester", actualJob1SelectIndex);
		
//		Step 09 - Kiểm tra dropdown có đủ 5 giá trị
		int numberSelect=Job1Select.getOptions().size();
		Assert.assertEquals(5, numberSelect);
	}
	
	@BeforeMethod
	 public void beforeMethod() {
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	  public void afterMethod() {
		//driver.quit();
	  }
}
