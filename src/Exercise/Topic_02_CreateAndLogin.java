package Exercise;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_02_CreateAndLogin {
	WebDriver driver;
  @Test
  public void TC_01_LoginEmpty() {
	  driver.findElement(By.xpath(".//*[@id='send2']")).click();
	  
	  driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("");
	  driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("");
	  String MesEmailBlank=driver.findElement(By.xpath(".//*[@id='advice-required-entry-email']")).getText();
	  Assert.assertEquals(MesEmailBlank, "This is a required field.");
	  
	  String MesPasBlank=driver.findElement(By.xpath(".//*[@id='advice-required-entry-pass']")).getText();
	  Assert.assertEquals(MesPasBlank, "This is a required field.");
  }
  
  @Test
  public void TC_02_LoginWithEmailInvalid() {
	  driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.xpath(".//*[@id='send2']")).click();
	  String MesEmailInvalid=driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText();
	  Assert.assertEquals(MesEmailInvalid, "Please enter a valid email address. For example johndoe@domain.com.");
  }
  
  @Test
  public void TC_03_LoginWithPassNho6Character() {
	  driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation@gmail.com");
	  driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123");
	  driver.findElement(By.xpath(".//*[@id='send2']")).click();
	  String MesPassInvalid=driver.findElement(By.xpath(".//*[@id='advice-validate-password-pass']")).getText();
	  Assert.assertEquals(MesPassInvalid, "Please enter 6 or more characters without leading or trailing spaces.");
  }
  
  @Test
  public void TC_04_LoginWithPassIncorrect() {
	  driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation@gmail.com");
	  driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123123123");
	  driver.findElement(By.xpath(".//*[@id='send2']")).click();
	  String MesPassInvalid1=driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
	  Assert.assertEquals(MesPassInvalid1, "Invalid login or password.");
  }
  
  @Test
  public void TC_05_CreateAnAccount() throws Exception {
	  driver.findElement(By.xpath(".//span[text()='Create an Account']")).click();
	  
	  driver.findElement(By.xpath(".//*[@id='firstname']")).sendKeys("firstname");
	  driver.findElement(By.xpath(".//*[@id='middlename']")).sendKeys("middlename");
	  driver.findElement(By.xpath(".//*[@id='lastname']")).sendKeys("lastname");
	  
	  //random 1
	  Random RandomNumber= new Random();
	  int randomInt=RandomNumber.nextInt(10000);
	  //random2
	  DateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	  Calendar cal=Calendar.getInstance();
	  String RandomDataTime= dateFormat.format(cal.getTime());
	  //random 3
	  String Alphabet = "abc";
	  String RandomCharacterAl = RandomStringUtils.random(6, Alphabet);
	  //random 4
	  String RandomCharacter = RandomStringUtils.randomAlphabetic(6); 
	  //random 5
	  String RandomCharacterNumber= RandomStringUtils.randomAlphanumeric(6);// Thư viên commons-lang3
	  driver.findElement(By.xpath(".//*[@id='email_address']")).sendKeys("TrangTTH"+RandomCharacterNumber+"@gmail.com");
	  
	  driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("123456");
	  driver.findElement(By.xpath(".//*[@id='confirmation']")).sendKeys("123456");
	  
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  String MeRegisterDone=driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText();
	  Assert.assertEquals(MeRegisterDone, "Thank you for registering with Main Website Store.");
	  
	  driver.findElement(By.xpath("//span[text()='Account']")).click();
	  
	  driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	  
	  Thread.sleep(6000);
	  String Title1=driver.getTitle();
	  System.out.println("Tiêu đề navigate:"+Title1);
	  String Tieude1="Home page";
	  Assert.assertEquals(Title1, Tieude1);
  }
  
  @BeforeMethod
  public void beforeClass() {
	  driver=new FirefoxDriver();
	  driver.get("http://live.guru99.com");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
  }

  @AfterMethod
  public void afterClass() {
	 driver.close();
  }

}
