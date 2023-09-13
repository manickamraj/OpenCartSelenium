package com.qa.openCart.base;

import org.apache.log4j.MDC;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.openCart.constants.AppConst;

import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 01 : Login Page Requirements")
@Story("Story 01 : User Login successfull")
public class LoginPageTest extends baseTest{
	
	private final Logger logger = Logger.getLogger(LoginPageTest.class);
	
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginPageRightSideMenuLinksTest() {
	MDC.put("testClassName", this.getClass().getSimpleName());
	logger.info("this is a log message from Loginpage test");
	 	
	 int menuLinks = loginpage.getRightSideMenuLinks().size();
	 Assert.assertEquals(menuLinks, AppConst.LOGIN_PAGE_RMENU_LINKS_COUNT);
	}
	
} 
