package com.orangehrm.pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.framework.commons.WebCommons;
import com.orangehrm.framework.webdriver.WebDriverClass;

public class HomePage extends WebCommons {
	
	public ExtentTest Logger = WebDriverClass.getLogger();
	
	@FindBy(xpath ="//a[@id='welcome']")
	private WebElement welcomeLabel;
	
	By bywelcomeLabel = By.xpath("//a[@id='welcome']");
	
	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement logoutButton;

	By bylogoutButton = By.xpath("//a[text()='Logout']");

	By byLoginPanelHeading = By.xpath("//div[@id='logInPanelHeading']");
	
	public void verifyWhetherAppLoginIsSuccessful() throws IOException {
		Logger = WebDriverClass.getLogger();		
		try {
			WaitForElement(5, bywelcomeLabel);
			Logger.pass("Application Login is Successful");
		} catch (Exception e) {
			Logger.addScreenCaptureFromPath(TakeScreenshot(driver, "ApplicationLogin"));
			Logger.fail("Application Login is Not Successful");
			Assert.fail("Application Login is Not Successful");
		}
	}
		
	public void verifyApplicationLogout() throws IOException {
		Logger = WebDriverClass.getLogger();		
		try {
			Click(welcomeLabel);
			WaitForElement(5, bylogoutButton);
			Click(logoutButton);
			WaitForElement(10, byLoginPanelHeading);
			Logger.pass("Application Logout is Successful");
		} catch (Exception e) {
			Logger.addScreenCaptureFromPath(TakeScreenshot(driver, "ApplicationLogout"));
			Logger.fail("Application Logout is Not Successful");
			Assert.fail("Application Logout is Not Successful");
		}
	}
	
	public static HomePage getHomePage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), HomePage.class);
	}
	
}
