package com.orangehrm.pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.framework.commons.WebCommons;
import com.orangehrm.framework.webdriver.WebDriverClass;

public class LoginPage extends WebCommons {

	public ExtentTest Logger = WebDriverClass.getLogger();

	@FindBy(xpath = "//div[@id='logInPanelHeading']")
	private WebElement loginPanelHeader;

	@FindBy(xpath = "//input[@id='txtUsername']")
	private WebElement usernameTxtb;

	@FindBy(xpath = "//input[@id='txtPassword']")
	private WebElement passwordTxtb;

	@FindBy(xpath = "//input[@id='btnLogin']")
	private WebElement loginBtn;

	@FindBy(linkText = "Forgot your password?")
	private WebElement forgotPasswordLink;

	public void launchApplication() throws IOException {
		try {
			driver.get(appProperties().getProperty("Url"));
			Logger.pass("Application Launched Succesfully");
		} catch (Exception e) {
			Logger.addScreenCaptureFromPath(TakeScreenshot(driver, "Launch Application"));
			Logger.fail("Error while Login into the Application");
			Assert.fail("Error while Login into the Application");
		}
	}

	public void verifyApplicationTitle() throws IOException {
		if (getPageTitle().equals("OrangeHRM")) {
			Logger.pass("Application title matched");
		} else {
			Logger.addScreenCaptureFromPath(TakeScreenshot(driver, "ApplicatonTitle"));
			Logger.fail("Application Title is not Matched");
			Assert.fail("Application Title is not Matched");
		}
	}
	
	public void LoginIntoApplication(String username,String password) throws IOException {
		System.out.println("Credentials"+ username+" === " +password);
		Logger = WebDriverClass.getLogger();
		Logger.info("Credentials :  "+ username+" === " +password);
		try {
			EnterInfo(usernameTxtb,username);
			EnterInfo(passwordTxtb,password);
			Click(loginBtn);
			Logger.pass("Updated username&Password and Application Login is completed");
		} catch (Exception e) {
			Logger.fail("Error while Login into the application");
			Logger.addScreenCaptureFromPath(WebCommons.TakeScreenshot(driver, "AppplicationLogin"));
			Assert.fail("Error while Login into the application");
		}
	}

	public static LoginPage getLoginPage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), LoginPage.class);
	}
}
