package com.qa.openCart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public DriverOptionsManager options;
	
	
	public static String highlight; 
	public static String testDataPath;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method initialize properties data to a prop object
	 * @return : Config.Properties object
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream fis = null;
		String envToExecute = System.getProperty("env");
		System.out.println("Execution is happening in environment :" +envToExecute);
		
		try {
		if (envToExecute != null) {	
		if (envToExecute.equalsIgnoreCase("qa")) {
			fis = new FileInputStream("./src/test/resources/properties/qa.config.properties");
		} 
		else {
			fis = new FileInputStream("./src/test/resources/properties/config.properties");
		}
		}
		else {
			fis = new FileInputStream("./src/test/resources/properties/config.properties");
		}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public WebDriver initDriver(Properties prop) {
		
		highlight = prop.getProperty("highlight");
		testDataPath = prop.getProperty("testdata");
		options = new DriverOptionsManager(prop);
		String browserName = prop.getProperty("browser");
		if (browserName.contentEquals("chrome")) {
			//driver = new ChromeDriver(options.getChromeOptions());
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			}
			else
			tlDriver.set(new ChromeDriver(options.getChromeOptions()));
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver(options.getEdgeOptions());
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			}
			else
			tlDriver.set(new EdgeDriver(options.getEdgeOptions()));
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			//driver = new EdgeDriver(options.getEdgeOptions());
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			}
			else
			tlDriver.set(new FirefoxDriver(options.getFireFoxOptions()));
		}
		else {
			System.out.println("Pass the correct browser name - chrome, edge or firefox" + browserName);
		
	}
		return getDriver();
	}
	
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	private void init_remoteWebDriver(String browser) {
		System.out.println("Execution happening on remote server via browser " +browser);
		try {
		switch (browser.toLowerCase()) {
		case "chrome":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), options.getChromeOptions()));
			break;
		case "edge":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), options.getEdgeOptions()));
			break;
		case "firefox":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), options.getFireFoxOptions()));
			break;
		default :
			System.out.println("Pass the correct browser name - chrome, edge or firefox" + browser);
			break;
		} 
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
