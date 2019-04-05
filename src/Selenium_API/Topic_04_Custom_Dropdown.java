package Selenium_API;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor javascript;
	@Test
	public void TC_01_Custom_DropDown_Jquery() throws Exception {//ok
		
	//	Step 02 - Ch�?n item cuối cùng: số 19
	//	Step 03 - Kiểm tra item đã được ch�?n thành công
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		
		}
	@Test
	public void TC_02_Custom_DropDown_angular() throws Exception {//ok

		driver.get("https://material.angular.io/components/select/examples");
		
		selectCustomDropdownList("//mat-select[@id='mat-select-5']//div[@class='mat-select-value']", "//mat-option", "Washington");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Washington']")).isDisplayed());
		
		selectCustomDropdownList("//mat-select[@id='mat-select-5']//div[@class='mat-select-value']", "//mat-option", "New York");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='New York']")).isDisplayed());
		
		}
	@Test
	public void TC_03_Custom_DropDown_KendoUI() throws Exception {//OK
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");// ch�?n mũ màu vàng
		selectCustomDropdownList("//div[@id='cap-view']/span[1]/span", "//ul[@id='color_listbox']/li", "Orange");// 
		Thread.sleep(20000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='cap-view']//span[text()='Orange']")).isDisplayed());
	}
	@Test
	public void TC_04_Custom_DropDown_Vue() throws Exception {//OK
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectCustomDropdownList("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains (text(),'Second Option')]")).isDisplayed());
	}
	@Test
	public void TC_05_Custom_Dropdow_jqueryEditableSelect() throws Exception {//ok
		
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		//Basic......................................
		Select Select1=new Select(driver.findElement(By.xpath("//select[@id='base']")));
		Select1.selectByVisibleText("Audi");
		String actualJob1SelectText=Select1.getFirstSelectedOption().getText();
		Assert.assertEquals("Audi", actualJob1SelectText);
		
		//Effects: Có thể tìm kiếm hoặc ch�?n
		driver.findElement(By.xpath("//div[@id='default-place']/input[@class='form-control es-input']")).sendKeys("A", Keys.RETURN);
		driver.findElement(By.xpath("//div[@id='default-place']/ul/li[text()='Audi']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']//li[@class='es-visible' and text()='Audi']")).isDisplayed());
		
		//Dialog- Mở ra hôp thoại, tìm kiếm/ ch�?n dl
		driver.findElement(By.xpath("//a[@class='btn btn-default']")).click();
		driver.findElement(By.xpath("//div[@id='appendTo-place']/input[@class='form-control es-input']")).sendKeys("A", Keys.RETURN);
		
		driver.findElement(By.xpath("//div[@id='appendTo-place']/ul/li[text()='Audi']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='appendTo-place']/ul/li[@class='es-visible' and text()='Audi']")).isDisplayed());
		
	}
	@Test
	public void TC_06_Custom_Dropdow_multipleItems() throws Exception {//OK
		driver.get("http://multiple-select.wenzhixin.net.cn/examples/#multiple-items.html");
		
		WebElement a=driver.findElement(By.xpath("//div[@class='content']/iframe"));
		driver.switchTo().frame(a);
		
		WebElement parent=driver.findElement(By.xpath("//button[@class='ms-choice']"));
		javascript.executeScript("arguments[0].click();", parent);
		
		Thread.sleep(1000);
		//WebElement cl1=driver.findElement(By.xpath("//li[@class='multiple']/label/input[@value='1']"));// click vào giá trị cần ch�?n
		//javascript.executeScript("arguments[0].click();", cl1);
		driver.findElement(By.xpath("//li[@class='multiple']/label/input[@value='1']")).click();// click vào giá trị cần ch�?n
		WebElement cl2=driver.findElement(By.xpath("//li[@class='multiple']/label/input[@value='2']"));// click vào giá trị cần ch�?n
		javascript.executeScript("arguments[0].click();", cl2);
		WebElement cl3=driver.findElement(By.xpath("//li[@class='multiple']/label/input[@value='3']"));
		javascript.executeScript("arguments[0].click();", cl3);
		WebElement cl4=driver.findElement(By.xpath("//li[@class='multiple']/label/input[@value='4']"));
		javascript.executeScript("arguments[0].click();", cl4);
		//driver.findElement(By.xpath("//li[@class='multiple']/label/input[@value='2']")).click();// click vào giá trị cần ch�?n
	
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='example']/div/button/span[text()='4 of 30 selected']")).isDisplayed());
	}
	@Test
	public void TC_07_Custom_Dropdow_multipleItems() throws Exception {//OK
		driver.get("https://semantic-ui.com/modules/dropdown.html ");//Multiple Selection
		
		driver.findElement(By.xpath("//div[@class='ui fluid dropdown selection multiple']//i[@class='dropdown icon']")).click();// click vào dropdow

		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()='Python']")).click();// click vào giá trị cần ch�?n
		driver.findElement(By.xpath("//div[text()='Javascript']")).click();
		driver.findElement(By.xpath("//div[text()='HTML']")).click();
		
		Thread.sleep(3000);
		
		List<WebElement> numberSelected=driver.findElements(By.xpath("//a[@class='ui label transition visible']"));//xác nhận giá trị ch�?n
		System.out.println("Phan tu duoc chon:"+numberSelected.size());
		Assert.assertEquals(3, numberSelected.size());
	}
		
	public void selectCustomDropdownList(String parentXpath, String childXpath, String valueExpected) throws Exception {
		
			//click mở dropdown ra
			WebElement parent=driver.findElement(By.xpath(parentXpath));
			javascript.executeScript("arguments[0].click();", parent);
			Thread.sleep(1000);
			// wait các item được hiển thị
			List <WebElement> child=driver.findElements(By.xpath(childXpath));
			wait.until(ExpectedConditions.visibilityOfAllElements(child));
			Thread.sleep(1000);
			//Get text cá item ra và kiểm tra = giá trị mong muốn
			//for(int i=1; i<=child.size();i++) {}
			
			for(WebElement childItem: child) {
				if(childItem.getText().equals(valueExpected)) {
					//Scrool đến item cần ch�?n xong click

					javascript.executeScript("arguments[0].scrollIntoView(true);", childItem);
					Thread.sleep(3000);
					childItem.click();
					//javascript.executeScript("arguments[0].click();", childItem); // dùng với TC_03, thay hàm click trên
					break;
				}
			}
			
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
