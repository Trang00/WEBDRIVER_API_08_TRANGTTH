package Exercise;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_09_UploadFile {
WebDriver driver;
	
	String rootFoder= System.getProperty("user.dir");
	String fileName01="01.jpg";
	String fileName02="02.jpg";
	String fileName03="03.jpg";

	String filePath01=rootFoder+"\\uploadFile\\"+fileName01;	//đường dẫn file cần upload
	String filePath02=rootFoder+"\\uploadFile\\"+fileName02;
	String filePath03=rootFoder+"\\uploadFile\\"+fileName03;
	
	@Test
	public void TC_01_UploadFileBySendkeys_Single() {
		
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement addFileButton=driver.findElement(By.xpath("//input[@type='file']"));
		addFileButton.sendKeys(filePath01);
	}
	
	@Test
	public void TC_02_UploadFileBySendkeys_Multiple() throws Exception  {
		
		//Step 01 - Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		Thread.sleep(5000);
		
		//Step 02 - Sử dụng phương thức sendKeys để upload file nhiều file cùng lúc chạy cho 3 trình duyệt (IE/ Firefox/ Chrome). Upload 3 file
		//IE => Chưa OK
		//Firefox => fiên bản mới nhất, cùng với driver tương ứng, slelenium 3.xx
		//Chrome=> OK
		WebElement addFileButton=driver.findElement(By.xpath("//input[@type='file']"));
		addFileButton.sendKeys(filePath01+"\n"+filePath02+"\n"+filePath03);
			
		//Step 03 - Kiểm tra file đã được chọn thành công
		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName03+"']")).isDisplayed());
		
		//Step 04 - Click Start button để upload cho cả 3 file
		List <WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for(WebElement starBtn:startButtons) {
			starBtn.click();
			Thread.sleep(2000);
		}
		
		//Step 05 - Sau khi upload thành công verify cả 3 file đã được upload
		Assert.assertTrue(driver.findElement(By.xpath("//a[@href and text()='"+fileName01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@href and text()='"+fileName02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@href and text()='"+fileName03+"']")).isDisplayed());
}

	@Test
	public void TC_03_AutoIT() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Click to add file button -> Open file dialog
		//driver.findElement(By.xpath("//span[contains(text(),'Add files...')]")).click();
		
		if (driver.toString().toLowerCase().contains("chrome")) {
		    driver.findElement(By.cssSelector(".fileinput-button")).click();
		    } else if (driver.toString().toLowerCase().contains("firefox")) {
		    clickToElementByJS("//input[@type='file']");
		  }else if(driver.toString().toLowerCase().contains("internet explorer")){
			  driver.findElement(By.xpath("//span[contains(text(),'Add files...')]")).click();
		  }
		
		//Execute 1 file exe
		if (driver.toString().toLowerCase().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { ".\\uploadFile\\chrome.exe", filePath01 });
		    } else if (driver.toString().toLowerCase().contains("firefox")) {
		    	Runtime.getRuntime().exec(new String[] { ".\\uploadFile\\firefox.exe", filePath01 });
		  }else {
			  Runtime.getRuntime().exec(new String[] { ".\\uploadFile\\ie.exe", filePath01 });
		  }
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName01+"']")).isDisplayed());
		//driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
			
		List <WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for(WebElement starBtn:startButtons) {
			starBtn.click();
			Thread.sleep(2000);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[@href and text()='"+fileName01+"']")).isDisplayed());
	
	}
	@Test
	public void TC_04_Robot() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Specify the file location with extension
		StringSelection select = new StringSelection(filePath01);

		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		//Click
		//driver.findElement(By.className("fileinput-button")).click();
		if (driver.toString().toLowerCase().contains("chrome")) {
		    driver.findElement(By.cssSelector(".fileinput-button")).click();
		    } else if (driver.toString().toLowerCase().contains("firefox")) {
		    clickToElementByJS("//input[@type='file']");
		  }else if(driver.toString().toLowerCase().contains("internet explorer")){
			  driver.findElement(By.xpath("//span[contains(text(),'Add files...')]")).click();
		  }

		Thread.sleep(3000);
		
		Robot robot = new Robot();
		Thread.sleep(1000);

		//ENTER
		robot.keyPress(KeyEvent.VK_ENTER);// nhấn phím enter
		robot.keyRelease(KeyEvent.VK_ENTER);// nhả phím enter

		//CONTROL +V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		//CONTROL +V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		//ENTER
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName01+"']")).isDisplayed());
		//driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
			
		List <WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for(WebElement starBtn:startButtons) {
			starBtn.click();
			Thread.sleep(2000);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[@href and text()='"+fileName01+"']")).isDisplayed());
		
		
	}
	@Test
	public void TC_05_UploadFile() throws Exception {
		
		//Step 01 - Open URL: 'https://encodable.com/uploaddemo/'
		driver.get("https://encodable.com/uploaddemo/");
		
		//Step 02 - Choose Files to Upload (Ex: UploadFile.jpg)
		WebElement ChooseFile=driver.findElement(By.xpath("//input[@id='uploadname1']"));
		ChooseFile.sendKeys(filePath01);
		
		//Step 03 - Select dropdown (Upload to: /uploaddemo/files/)
		Select UploadTo= new Select(driver.findElement(By.xpath("//select[@class='upform_field picksubdir_field']")));
		UploadTo.selectByValue("/");
		
		//Step 04 - Input random folder to 'New subfolder? Name:) textbox (Ex: dam1254353)
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys("Trang00");
		
		//Step 05 - Input email and firstname (dam@gmail.com/ DAM DAO)
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys("dam@gmail.com");
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys("DAM DAO");
		
		//Step 06 - Click Begin Upload (Note: Wait for page load successfully)
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		Thread.sleep(3000);
		
		//Step 07 - Verify information
		  //  + Email Address: dam@gmail.com/ First Name: DAM DAO
		  //  + File name: UploadFile.jpg
		Assert.assertTrue(driver.findElement(By.xpath("//dd[text()='Email Address: dam@gmail.com']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dd[text()='First Name: DAM DAO']")).isDisplayed());
				
		//Step 08 - Click 'View Uploaded Files' link
		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		
		//Step 09 - Click to random folder (Ex: dam1254353)
		driver.findElement(By.xpath("//a[contains(text(),'Trang00')]")).click();
		
		//Step 09 - Verify file name exist in folder (UploadFile.jpg)
		Assert.assertTrue(driver.findElement(By.xpath("//a[@href and text()='"+fileName01+"']")).isDisplayed());
	}

	public Object clickToElementByJS(String xpathName) {
    	WebElement element=driver.findElement(By.xpath(xpathName));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].click();", element);
    }
	public int randomNumber() {
		Random random= new Random();
		int number=random.nextInt(9999);
		return number;
	}
		
@BeforeMethod
public void beforMethod() {
	
	System.out.println(rootFoder);
	System.out.println(filePath01);
	System.out.println(filePath02);
	System.out.println(filePath03);
	//driver= new FirefoxDriver();	
	
	System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	driver= new ChromeDriver();
	
	//System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
//	driver= new FirefoxDriver();
	
	//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
	//driver= new InternetExplorerDriver();
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();

}
@AfterMethod
public void afterMethod() {
	driver.quit();
}
}
