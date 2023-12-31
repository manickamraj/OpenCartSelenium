package com.qa.openCart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.openCart.factory.DriverFactory;
import com.qa.openCart.pageClass.AccountPage;
import com.qa.openCart.pageClass.LoginPage;
import com.qa.openCart.pageClass.ProductInfoPage;
import com.qa.openCart.pageClass.RegisterPage;
import com.qa.openCart.pageClass.SearchPage;
import com.qa.openCart.utilities.ExcelUtil;

public class baseTest {

	DriverFactory df;
	WebDriver driver;
	
	public Properties prop;
	
	protected ExcelUtil excelUtil;
	
	protected LoginPage loginpage;
	protected AccountPage accountpage;
	protected SearchPage searchpage;
	protected ProductInfoPage prodinfopage;
	protected RegisterPage registerpage;
	
	protected SoftAssert softAssert;
		
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) throws InterruptedException {
		df = new DriverFactory();
		prop = df.initProp();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		loginpage = new LoginPage(driver);
		softAssert = new SoftAssert();
		excelUtil = new ExcelUtil();
	}
	
	
	@AfterTest
	public void teardown() throws InterruptedException {
		Thread.sleep(2000);
		if(driver != null){
		    driver.quit();
		   }
	} 
}
