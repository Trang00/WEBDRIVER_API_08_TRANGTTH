package Selenium_API;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Checkbox_Extend {
	WebDriver driver;
	JavascriptExecutor javascript;
	
	@Test
	public void TC_SingleCheckBox() throws Exception {
		driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
		WebElement element = driver.findElement(By.xpath("//input[@id='isAgeSelected']"));
		javascript.executeScript("arguments[0].click()", element);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='txtAge' and text()='Success - Check box is checked']")).isDisplayed());
		Thread.sleep(3000);
	if(element.isSelected())
		{
			javascript.executeScript("arguments[0].click()", element);
		Thread.sleep(3000);
		}
	}
	@Test
	public void TC_MultipleCheckbox() throws Exception {
		driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
		WebElement checkboxBtn = driver.findElement(By.xpath("//input[@id='check1']"));
		checkboxBtn.click();
		List<WebElement> checked = driver.findElements(By.xpath("//div[@class='panel-heading' and text()='Multiple Checkbox Demo']/following-sibling::div//input[@type='checkbox']"));
		int size = checked.size();
		System.out.println(size);
		for(int i =0; i< size; i++)
		{
			Assert.assertTrue(checked.get(i).isSelected());
		}
		Thread.sleep(3000);
	}
	@Test
	public void TC_02_MultipleCheckbox(){
		driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
		
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@class='cb1-element']"));
        for(int i=0; i<checkBoxes.size(); i=i+2){
            checkBoxes.get(i).click();
        }
        int checkedCount=0, uncheckedCount=0;
        for(int i=0; i<checkBoxes.size(); i++){
            System.out.println(i+" checkbox is selected "+checkBoxes.get(i).isSelected());
            if(checkBoxes.get(i).isSelected()){
                checkedCount++;
            }else{
                uncheckedCount++;
            }
        }
        System.out.println("Number of selected checkbox: "+checkedCount);
        System.out.println("Number of unselected checkbox: "+uncheckedCount);
	}
	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		javascript = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@AfterClass
	public void afterClass() {
			driver.quit();
	}
}
