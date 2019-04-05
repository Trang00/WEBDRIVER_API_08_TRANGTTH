package Selenium_API;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_07_WindowPopup {
WebDriver driver;
JavascriptExecutor javascript;
@Test
public void TC_01_WindowID() {
	/*Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
Step 02 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
Step 03 - Kiểm tra title của window mới = Google
Step 04 - Close window mới
Step 05 - Switch v�? parent window
Step 06 - Kiểm tra đã quay v�? parent window thành công (title/ url)
	 * */
	driver.get("https://daominhdam.github.io/basic-form/index.html");
	
	String parentWindow = driver.getWindowHandle();
	System.out.println("parenWindow:"+parentWindow);
	
	driver.findElement(By.xpath("//a[@target='_blank']")).click(); //a[text()='Click Here']
	
	switchToChildWindowID(parentWindow);
	
	Assert.assertEquals(driver.getTitle(), "Google");
	
	closeAllWithoutParentWindows(parentWindow);//driver.close();	// đóng tab đang hoạt động
	
	Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
}
@Test
public void TC_02_WindowTitle() {// cách 2 của TC 1
	
	driver.get("https://daominhdam.github.io/basic-form/index.html");
	String parentWindow = driver.getWindowHandle();
	System.out.println("parenWindow:"+parentWindow);
	driver.findElement(By.xpath("//a[@target='_blank']")).click();
	switchToWindowByTitle("Google");
	Assert.assertEquals(driver.getTitle(), "Google");
	closeAllWithoutParentWindows(parentWindow);
	Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
}
@Test
public void TC_03_Window() {// cách 3 (sưu tầm) của TC 1
	
	driver.get("https://daominhdam.github.io/basic-form/index.html");
	// Launching the site.
	driver.findElement(By.xpath("//a[@target='_blank']")).click();
	String MainWindow = driver.getWindowHandle();
	// To handle all new opened window.
	Set<String> s1 = driver.getWindowHandles();
	System.out.println("parentWindow s1:"+s1);
	Iterator<String> i1 = s1.iterator();
	System.out.println("i:"+i1);
	while (i1.hasNext()) {
		String ChildWindow = i1.next();
		if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
			// Switching to Child window
			driver.switchTo().window(ChildWindow);
			String TitleChild=driver.getTitle();
			System.out.println("TitleChild:"+TitleChild);
			Assert.assertEquals(TitleChild, "Google");
			driver.close();// Closing the Child Window.
		}
	}
	// Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow);
	String ParentWindow=driver.getTitle();
	System.out.println("ParentWindow:"+ParentWindow);
	Assert.assertEquals(ParentWindow, "SELENIUM WEBDRIVER FORM DEMO");
}
@Test
public void TC_04() {
	/*Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
Step 02 - Kiểm tra và close quảng cáo nếu có xuất hiện
Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
Step 06- Click CSR link on Privacy Policy page
Step 07 - Close tất cả popup khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
	 */
	driver.get("http://www.hdfcbank.com/");
	
	List <WebElement> notificationsIframe =driver.findElements(By.xpath("//div[@id='vizury-notification-container']/iframe"));
	int sizeIframe=notificationsIframe.size();
	System.out.println("SizeIframe:"+sizeIframe);
	if(notificationsIframe.size()>0) {
		Assert.assertTrue(notificationsIframe.get(0).isDisplayed());
		driver.switchTo().frame(notificationsIframe.get(0));
		javascript.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='div-close']")));
		driver.switchTo().defaultContent();// chính là top Window (parent)
	}
	//Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
	String parentWindow= driver.getWindowHandle();
	driver.findElement(By.xpath("//a[text()='Agri']")).click();
	switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
	
	//Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
	driver.findElement(By.xpath("//p[text()='Account Details']")).click();
	switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	
	//Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
	driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
	driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();//
	
	switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	
	//Step 06- Click CSR link on Privacy Policy page
	driver.findElement(By.xpath("//a[text()='CSR']")).click();
	//driver.switchTo().defaultContent();
	
	Assert.assertEquals(driver.getTitle(), "HDFC BANK - CSR - Homepage");
	
	closeAllWithoutParentWindows(parentWindow);
}


@Test
public void TC_05() {
	
		//Step 01 - Truy cập vào trang: http://live.guru99.com/index.php/
	  driver.get("http://live.guru99.com/index.php/");
	  
	  	//Step 02 - Click vào Mobile tab
	  driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	  
	  //Step 03 - Add sản phẩm Sony Xperia vào để Compare (Add to Compare)
	driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//a[@class='link-compare']")).click();
		
		//Step 04 - Add sản phẩm Samsung Galaxy vào để Compare (Add to Compare)
	driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//a[@class='link-compare']")).click();
  
		//Step 05 - Click to Compare button
	driver.findElement(By.xpath("//button[@title='Compare']")).click();
	
		//Step 06 - Switch qa cửa sổ mới (chứa 2 sản phẩm đã được Add vào để Compare)
	
	switchToWindowByTitle("Products Comparison List - Magento Commerce");
	
		//Step 07 - Verify title của cửa sổ bằng:  Products Comparison List - Magento Commerce
	Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
	
		//Step 08 - Close tab và chuyển v�? Parent Window
	driver.close();
	 
	
}
// Handle only 2 window
public void switchToChildWindowID(String parent) {
	//get ra tất cả các cửa sổ đang có, 
    Set<String> allWindows = driver.getWindowHandles();
    // Duyệt qua từng ID của tất cả các cửa sổ
    for (String childWindow : allWindows) {
    	System.out.println("ChildWindowID:"+childWindow);// ID định danh cho 1 kiểu dữ liệu duy nhất
    	//Kiểm tra nếu như ID nào khác với parentID thì switch qua cửa sổ đó 
                if (!childWindow.equals(parent)) {
                            driver.switchTo().window(childWindow);
                            break;
                }
    }
}

public void switchToWindowByTitle(String titleExpected) {
	//Get ra tất cả các cửa sổ đang có
    Set<String> allWindows = driver.getWindowHandles();
    //Dùng vòng lặp để duyệt qua từng ID của tất cả các cửa sổ
    for (String childWindows : allWindows) {
    	//Switch qua từng window
                driver.switchTo().window(childWindows);
                //Get ra title của window/tab hiện tại
                String currentWinTitle = driver.getTitle();
                System.out.println("currentWinTitle:"+currentWinTitle);
                //Kiểm tra nêwu như  currentWinTitle=titleExpected thì dừng lại, thoát kh�?i vòng lặp
                if (currentWinTitle.equals(titleExpected)) {
                            break;
                }
    }
}

public boolean closeAllWithoutParentWindows(String parentWindow) {
	//Get ra ID của tất cả các cửa sổ
    Set<String> allWindowID = driver.getWindowHandles();
    // dùng vòng for để duyệt qua từng ID
    for (String childWindows : allWindowID) {
    	//KT nếu ID nào khác với parentID thì switch qua
                if (!childWindows.equals(parentWindow)) {
                	//Switcj qia cửa dố ID đó
                            driver.switchTo().window(childWindows);
                            //Close nó
                            driver.close();
                }
    }
    driver.switchTo().window(parentWindow);// switch v�? lại parentWindow
    //Kiểm tra chỉ còn duy nhất 1 cửa sổ
    if (driver.getWindowHandles().size() == 1)
               return true;
    else
               return false;
}

@BeforeMethod
public void beforeMethod() {
	driver=new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
}

@AfterMethod
public void afterMethod() {
	driver.quit();// đóng cả trình duyệt
}
}
