package com.qa.openCart.pageClass;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.utilities.ElementUtil;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	By accountMenuLink = By.xpath("(//ul[@class='breadcrumb']/li/a)[2]");
	By searchTextBox = By.xpath("//input[@name='search']");
	By searchButton = By.xpath("//div[@id='search']//button");
	
	@Step("get accountPageMenuLink")
	public String accountPageMenuLink() {
		return eleUtil.getTextFindElement(accountMenuLink);
	}
	
	@Step("Search the products")
	public SearchPage searchProducts(String searchKey) {
		eleUtil.clearTextField(searchTextBox);
		eleUtil.doSendKeys(searchTextBox, searchKey);
		saveScreenshotPNG(driver);
		eleUtil.clickButton(searchButton);
		return new SearchPage(driver);
	}
	
	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}
