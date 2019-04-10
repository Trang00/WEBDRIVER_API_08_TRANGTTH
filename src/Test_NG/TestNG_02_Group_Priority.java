package Test_NG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_02_Group_Priority {
	
  @Test(groups="user",enabled=true)
  public void TC_01() {
  }
  @Test(groups="product", enabled=false)
  public void TC_02() {
  }
  @Test(groups="payment", priority=1)
  public void TC_03() {
  }
  @Test(groups="user")
  public void TC_04() {
  }
  @Test(groups="user",priority=2)
  public void TC_05() {
  }
  
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

 }
