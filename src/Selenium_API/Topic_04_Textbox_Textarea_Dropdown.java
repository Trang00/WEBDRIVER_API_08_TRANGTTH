package Exercise;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_04_Textbox_Textarea_Dropdown {
  WebDriver driver;
	@Test
  public void TC_01_Textbox_TextArea_NewCustomer() throws Exception {
		
	//step 1 ,2 -> befor
		String homePageText = driver.findElement(homepageTextLocator).getText();
		Assert.assertEquals(homePageText, "Welcome To Manager's Page of Guru99 Bank");
		
		// cách 2 truyền thẳng
		Assert.assertTrue(driver.findElement(homepageTextLocator).isDisplayed());
		
	//step 3	- click link new Customer
		driver.findElement(NewCustomerLocator).click();
		
	//step 4 // nhập thông tin đúng và ấn submit
		driver.findElement(CustomerNameLocator).sendKeys(CustomerName);
		driver.findElement(GenderLocator).click();
		driver.findElement(DataOfBirthLocator).sendKeys(Birthdate);
		driver.findElement(AddressLocator).sendKeys(Address);
		driver.findElement(CityLocator).sendKeys(City);
		driver.findElement(StateLocator).sendKeys(State);
		driver.findElement(PINLocator).sendKeys(Pin);
		driver.findElement(MobileLocator).sendKeys(Mobile);
		driver.findElement(EmailLocator).sendKeys(Email);
		driver.findElement(PassLocator).sendKeys(Password);
		driver.findElement(SubmitNewCustomerLocator).click();
		
	//step 5 get thông tin customer ID
		String CustomerID=driver.findElement(CustomerIDLocator).getText();
		System.out.println("Customer ID: "+CustomerID);
		
	//step 6 verify các thông tin được tạo mới thành công
		Assert.assertEquals(driver.findElement(CustomerIDLocator).getText(), CustomerID);
		Assert.assertEquals(driver.findElement(VCustomerNameLocator).getText(), CustomerName);
		Assert.assertEquals(driver.findElement(VDataOfBirthLocator).getText(), Birthdate);
		Assert.assertEquals(driver.findElement(VAddressLocator).getText(), Address);
		Assert.assertEquals(driver.findElement(VCityLocator).getText(), City);
		Assert.assertEquals(driver.findElement(VStateLocator).getText(), State);
		Assert.assertEquals(driver.findElement(VPINLocator).getText(), Pin);
		Assert.assertEquals(driver.findElement(VMobileLocator).getText(), Mobile);
		Assert.assertEquals(driver.findElement(VEmailLocator).getText(), Email);
	//Step 07 - Chọn menu Edit Customer > Nhập Customer ID > Submit
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		
		driver.findElement(CustomerIDLoginEditLocator).sendKeys(CustomerID);
		driver.findElement(SubmitIDLoginEditLocator).click();
	//Step 08 - Tại màn hình Edit Customer: Verify giá trị tại 2 field: Customer Name và Address 
		Assert.assertEquals(driver.findElement(CustomerNameLocator).getAttribute("Value"), CustomerName);
		Assert.assertEquals(driver.findElement(AddressLocator).getText(), Address);
	//Step 09 - Nhập giá trị mới tại tất cả các field (ngoại trừ những field bị disable) > Submit
	
		driver.findElement(AddressLocator).clear();
		driver.findElement(AddressLocator).sendKeys(editAddress);
		driver.findElement(CityLocator).clear();
		driver.findElement(CityLocator).sendKeys(editCity);
		driver.findElement(StateLocator).clear();
		driver.findElement(StateLocator).sendKeys(editState);
		driver.findElement(PINLocator).clear();
		driver.findElement(PINLocator).sendKeys(editPin);
		driver.findElement(MobileLocator).clear();
		driver.findElement(MobileLocator).sendKeys(editMobile);
		driver.findElement(EmailLocator).clear();
		driver.findElement(EmailLocator).sendKeys(editEmail);
		driver.findElement(SubmitNewCustomerLocator).click();
		Thread.sleep(5000);
		
//		Step 10 - Verify giá trị các field đúng với dữ liệu sau khi đã Edit thành công
		Assert.assertEquals(driver.findElement(VAddressLocator).getText(), editAddress);
		Assert.assertEquals(driver.findElement(VCityLocator).getText(), editCity);
		Assert.assertEquals(driver.findElement(VStateLocator).getText(), editState);
		Assert.assertEquals(driver.findElement(VPINLocator).getText(), editPin);
		Assert.assertEquals(driver.findElement(VMobileLocator).getText(), editMobile);
		Assert.assertEquals(driver.findElement(VEmailLocator).getText(), editEmail);
  }

	String ID="mngr174832";
	String Pass="azEzUtA";
	
	
	String CustomerID="";
	String CustomerName="Selenium Online" ;
	String Birthdate="2000-10-01" ;
	String Address="123 Address" ;
	String City="Ho Chi Minh" ;
	String State="Thu Duc" ;
	String Pin="123456" ;
	String Mobile="123456987" ;
	String RandomCharacterAl = RandomStringUtils.randomAlphabetic(6); 
	String Email="TrangTTH"+RandomCharacterAl+"@gmail.com" ;
	String Password="123456" ;
	
	String editAddress="edit 123 Address" ;
	String editCity="edit Ho Chi Minh" ;
	String editState="edit Thu Duc" ;
	String editPin="999999" ;
	String editMobile="12349999" ;
		String editEmail="editTrang"+RandomCharacterAl+"@gmail.com" ;
	
	By NewCustomerLocator=By.xpath("//a[text()='New Customer']");
	
	By CustomerIDLoginEditLocator= By.xpath("//input[@name='cusid']");
	By SubmitIDLoginEditLocator=By.xpath("//input[@name='AccSubmit']");
	
	By homepageTextLocator=By.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]");
	
	// Locator login
	By IDLocator=By.xpath("//input[@name='uid']");
	By PassLocator=By.xpath("//input[@name='password']");
	By LoginLocator=By.xpath("//input[@name='btnLogin']");
	
	// Locator New
	By CustomerNameLocator=By.xpath("//input[@name='name']");
	By GenderLocator=By.xpath("//input[@name='rad1']");
	By DataOfBirthLocator=By.xpath("//input[@name='dob']");
	By AddressLocator=By.xpath("//textarea[@name='addr']");
	By CityLocator=By.xpath("//input[@name='city']");
	By StateLocator=By.xpath("//input[@name='state']");
	By PINLocator=By.xpath("//input[@name='pinno']");
	By MobileLocator=By.xpath("//input[@name='telephoneno']");
	By EmailLocator=By.xpath("//input[@name='emailid']");
	By PasswordLocator=By.xpath("//input[@name='password']");
	By SubmitNewCustomerLocator=By.xpath("//input[@value='Submit']");
	//Locator Verify
	By CustomerIDLocator=By.xpath("//td[text()='Customer ID']//following-sibling::td");
	By VCustomerNameLocator=By.xpath("//td[text()='Customer Name']//following-sibling::td");
	By VGenderLocator=By.xpath("//td[text()='Gender']//following-sibling::td");
	By VDataOfBirthLocator=By.xpath("//td[text()='Birthdate']//following-sibling::td");
	By VAddressLocator=By.xpath("//td[text()='Address']//following-sibling::td");
	By VCityLocator=By.xpath("//td[text()='City']//following-sibling::td");
	By VStateLocator=By.xpath("//td[text()='State']//following-sibling::td");
	By VPINLocator=By.xpath("//td[text()='Pin']//following-sibling::td");
	By VMobileLocator=By.xpath("//td[text()='Mobile No.']//following-sibling::td");
	By VEmailLocator=By.xpath("//td[text()='Email']//following-sibling::td");

  @BeforeMethod
  public void beforeMethod() {
	//step 1
			driver=new FirefoxDriver();
			driver.get("http://demo.guru99.com/v4");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		//step 2	- Login 
			driver.findElement(IDLocator).sendKeys(ID);
			driver.findElement(PassLocator).sendKeys(Pass);
			driver.findElement(LoginLocator).click();
  }

  @AfterMethod
  public void afterMethod() {
	driver.quit();
  }

}
