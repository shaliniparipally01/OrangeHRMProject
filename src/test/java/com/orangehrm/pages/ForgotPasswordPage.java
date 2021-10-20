package com.orangehrm.pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.framework.commons.WebCommons;
import com.orangehrm.framework.webdriver.WebDriverClass;

public class ForgotPasswordPage extends WebCommons{

	public ExtentTest Logger = WebDriverClass.getLogger();
	
	@FindBy(linkText="Forgot your password?")
	private WebElement forgotPasswordLink;
	
	@FindBy(xpath ="//input[@id='securityAuthentication_userName']")
	private WebElement usernameTxtb;
	
	@FindBy(xpath ="//input[@id='btnSearchValues']")
	private WebElement resetPasswordBtn;
	
	@FindBy(xpath ="//input[@id='btnCancel']")
	private WebElement cancelBtn;
	
	public void verifyForgotPasswordPageElements() throws IOException {
		Logger = WebDriverClass.getLogger();		
		try {
			Click(forgotPasswordLink);
			usernameTxtb.isDisplayed();
			resetPasswordBtn.isDisplayed();
			cancelBtn.isDisplayed();
			Logger.pass("Forgot Password Page Elements Available");
		} catch (Exception e) {
			Logger.addScreenCaptureFromPath(TakeScreenshot(driver, "ForgotPasswordPage"));
			Logger.fail("Forgot Password Page Elements not Available");
			Assert.fail("Forgot Password Page Elements not Available");
		}
	}
	
	public static ForgotPasswordPage getForgotPassword() {
		return PageFactory.initElements(WebDriverClass.getDriver(), ForgotPasswordPage.class);
	}

}
