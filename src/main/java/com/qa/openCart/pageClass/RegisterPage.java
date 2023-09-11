package com.qa.openCart.pageClass;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.constants.AppConst;
import com.qa.openCart.utilities.ElementUtil;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By registerLink = By.xpath("//aside[@id='column-right']//a[text()='Register']");
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailID = By.name("email");
	private By phoneNum = By.name("telephone");
	private By passwordFirst = By.xpath("//*[@id=\"input-password\"]");
	private By passwordconfirm = By.xpath("//*[@id=\"input-confirm\"]");
	private By radioButtonYes = By.xpath("//label[normalize-space()='Yes']//input[@type='radio']");
	private By radioButtonNo = By.xpath("//label[normalize-space()='No']//input[@type='radio']");
	private By agreeCheckBox = By.xpath("//input[@type='checkbox']");
	private By continueSubmitButton = By.xpath("//input[@value='Continue']");
	private By registerSuccessMsg = By.xpath("//div[@id='content']//h1");
	private By logoutLink = By.xpath("//aside[@id='column-right']//a[text()='Logout']");
	
	@Step("Navigate to Registration page")
	public void navigateToRegisterPage() {
		eleUtil.clickLink(registerLink);
	}
	
	@Step("Enter first name")
	public void enterFirstName(String firstName) {
		eleUtil.webDriverWaitFindElementOnPage(this.firstName,AppConst.LONG_TIME_OUT).sendKeys(firstName);
		saveScreenshotPNG(driver);
	}
	
	@Step("Enter last name")
	public void enterLastName(String lastName) {
		eleUtil.doSendKeys(this.lastName, lastName);
		saveScreenshotPNG(driver);
	}
	
	@Step("Enter emailID")
	public void enterEmailID(String emailID) {
		eleUtil.doSendKeys(this.emailID, emailID);
		saveScreenshotPNG(driver);
	}
	
	@Step("Enter phone number")
	public void enterPhoneNum(String phoneNum) {
		eleUtil.doSendKeys(this.phoneNum, phoneNum);
		saveScreenshotPNG(driver);
	}
	
	@Step("Enter password")
	public void enterPasswordFirst(String passwordFirst) {
		eleUtil.doSendKeys(this.passwordFirst, passwordFirst);
		saveScreenshotPNG(driver);
	}
	
	@Step("Confirm password")
	public void confirmPassword(String passwordconfirm) {
		eleUtil.doSendKeys(this.passwordconfirm, passwordconfirm);
		saveScreenshotPNG(driver);
	}
	
	@Step("Select subscription")
	public void selectSubscrice(String subscribe) {
		if(subscribe.equalsIgnoreCase("y")) {
		eleUtil.checkBox(this.radioButtonYes); }
		else {
			eleUtil.checkBox(this.radioButtonNo);}
		saveScreenshotPNG(driver);
		}
	
	@Step("Agree Terms & Conditions check box")
	public void selectAgreeCheckBox() {
		eleUtil.checkBox(agreeCheckBox);
		saveScreenshotPNG(driver);
	}
	
	@Step("Submit user registration")
	public String submitRegisterButton() {
		eleUtil.clickButton(continueSubmitButton);
		saveScreenshotPNG(driver);
		return eleUtil.webDriverWaitFindElementOnPage(registerSuccessMsg, AppConst.DEFAULT_TIME_OUT).getText();
	}
	
	@Step("Logout registered user")
	public void logoutUser() {
		eleUtil.clickLink(logoutLink);
	}
	
	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
 
/*	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}*/
}
