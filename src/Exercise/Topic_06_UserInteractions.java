package Exercise;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_06_UserInteractions {
	WebDriver driver;
	Actions action;
	String workingDirectory=System.getProperty("user.dir");
	String jsFilePath=workingDirectory+"\\helper\\drag_and_drop_helper.js";
	String jQueryFilePath=workingDirectory+"\\helper\\jquery_load_helper.js";
	
	@Test
	public void TC_01_HoverMouse() {
		/*
		Step 01 - Truy cập vào trang: http://www.myntra.com/
		Step 02 - Hover chuột vào Menu để login
		Step 03 - Chọn Login button
		Step 04 - Verify Login form được hiển thị
		Lưu ý: khi chạy tc này ko được sd chuột và bàn phím, chuột sẽ bị cướp quyền 
		*/
		driver.get("http://www.myntra.com/"); 
		
		WebElement avatarIcon=driver.findElement(By.xpath("//div[@class='desktop-user']/div[@class='desktop-userIconsContainer']"));
		action.moveToElement(avatarIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	}
	@Test
	public void TC_02_ClickAndHold() {
		/*Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/selectable/display-grid.html
			Step 02 - Click and hold từ 1-> 4
			Step 03 - Sau khi chọn kiểm tra rằng có đúng 4 phần tử đã được chọn thành công với xpath:
			//li[@class='ui-state-default ui-selectee ui-selected']
		 */
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> number=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.clickAndHold(number.get(0)).moveToElement(number.get(3)).release().perform();
		
		List<WebElement> numberSelected=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("Phan tu duoc chon:"+numberSelected.size());
		Assert.assertEquals(4, numberSelected.size());
	}
	@Test
	public void TC_02_ClickAndHold02() {
	// chọn từng phần tử
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> number=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
			
		action.keyDown(Keys.CONTROL).build().perform();
		number.get(0).click();
		number.get(2).click();
		number.get(4).click();
		number.get(6).click();
		number.get(8).click();
		action.keyUp(Keys.CONTROL).build().perform();
		
		List<WebElement> numberSelected=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("Phan tu duoc chon ... :"+numberSelected.size());
		Assert.assertEquals(5, numberSelected.size());
	}
	@Test
	public void TC_03_DoubleClick() {
		/*tep 01 - Truy cập vào trang: http://www.seleniumlearn.com/double-click
			Step 02 - Double click vào element: Double-Click Me!
			Step 03 - Verify text trong alert được hiển thị: 'The Button was double-clicked.'
			Step 04 - Accept Javascript alert
		 * */
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleButon=driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(doubleButon).perform();
		Alert doubleAlert=driver.switchTo().alert();
		Assert.assertEquals(doubleAlert.getText(), "The Button was double-clicked.");
		doubleAlert.accept();
	}
	@Test
	public void TC_04_RightClick() {
		/*Step 01 - Truy cập vào trang: http://swisnl.github.io/jQuery-contextMenu/demo.html
	Step 02 - Right click vào element: right click me
	Step 03 - Hover chuột vào element: Quit
	Step 04 - Verify element Quit (visible + hover) 
	Step 05 - Click chọn Quit
	Step 06 - Accept Javascript alert
	=> luu y luc chay ko dung chuot va ban phim
		 */
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement rightClickButton=driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClickButton).perform();
		
		WebElement quitVisible=driver.findElement(By.xpath("//li[contains(@class,'context-menu-item')]"));
		action.moveToElement(quitVisible).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-item')and contains(@class,'context-menu-visible')and contains(@class,'context-menu-hover')]")).isDisplayed());
		
		quitVisible.click();
		
		Alert doubleAlert=driver.switchTo().alert();
		doubleAlert.accept();
		
	}
	@Test
	public void TC_05_DragAndDrop() {
		/*Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/dragdrop/angular
		Step 02 - Kéo hình tròn nhỏ vào hình tròn lớn
		Step 03 - Verify message đã thay đổi: You did great!
		Có nguồn và đích đến
		 * */
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceButton = driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement targetButton = driver.findElement(By.xpath("//*[@id='droptarget']"));
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceButton, targetButton).build().perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
		
	}
	@Test
	public void TC_06_DragAndDropHTML5_Css() throws Exception {
		/*Step 01 - Truy cập vào trang: https://html5demos.com/drag/#
		Step 02 - Sử dụng Javascript/ Jquery để kéo thả element: one/ two/ three/.. vào thùng rác
		 => CHECK LẠI
		 * */
		driver.get("https://html5demos.com/drag/#");
		
		String oneCss="#one";
		String targetCss="#bin";
		
		String jQueryLoader=readFile(jQueryFilePath);
		String java_script=readFile(jsFilePath);
		
		// Inject Jquery vafo trinh duyet
		JavascriptExecutor je=(JavascriptExecutor) driver;
		je.executeScript(jQueryLoader);
		
		// thu thi doan lenh de keo tha
		java_script=java_script+"$(\""+oneCss+"\").simulateDragDrop({dropTarget:\""+targetCss+"\"});";
		je.executeScript(java_script);
		Thread.sleep(3000);
	}
	@Test
	public void TC_06_DragAndDropHTML5_Xpath() throws Exception {
		/*Step 01 - Truy cập vào trang: https://html5demos.com/drag/#
		Step 02 - Sử dụng Javascript/ Jquery để kéo thả element: one/ two/ three/.. vào thùng rác
				 * */
		driver.get("https://html5demos.com/drag/#");
		
		String oneXpath="//a[@id='one']";
		String targetXpath="//div[@id='bin']";
		
		drag_the_and_drop_html5_by_xpath(oneXpath, targetXpath);
		Thread.sleep(3000);
	}
	public String readFile(String file)throws IOException{
		Charset cs=Charset.forName("UTF-8");
		FileInputStream stream=new FileInputStream(file);
		try {
			Reader reader= new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder buider=new StringBuilder();
			char [] buffer= new char[8192];
			int read;
			while((read=reader.read(buffer,0,buffer.length))>0) {
				buider.append(buffer,0,read);
			}
			return buider.toString();
		}finally {
			stream.close();
		}
		}
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

    	WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}	
	@BeforeMethod
	 public void beforeMethod() {
		driver=new FirefoxDriver();
		action=new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	  public void afterMethod() {
		driver.quit();
	  }
}
