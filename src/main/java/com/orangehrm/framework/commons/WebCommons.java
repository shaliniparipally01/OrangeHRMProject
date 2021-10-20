package com.orangehrm.framework.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.orangehrm.framework.utilities.ReadDataFromPropertyFile;
import com.orangehrm.framework.webdriver.WebDriverClass;

public class WebCommons {

	public WebDriver driver = WebDriverClass.getDriver();

	// method to click on element with scroll
	public void Click(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		element.click();
	}

	// method to click on element with scroll
	public void JsClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
	}

	// method to enter information in text box
	public void EnterInfo(WebElement textboxElement, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", textboxElement);
		textboxElement.clear();
		textboxElement.sendKeys(value);
	}

	// method to scroll down till element
	public void ScrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	// method to get title
	public String getPageTitle() {
		return driver.getTitle();
	}

	// Method to take screenshot
	public static String TakeScreenshot(WebDriver driver, String screenshotname) throws IOException {
		TakesScreenshot screen = ((TakesScreenshot) driver); // to take screenshot
		File screenshotFile = screen.getScreenshotAs(OutputType.FILE); // to convert it as File
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotname + generateUniqueID()
				+ ".png";
		File screenPath = new File(screenshotPath);
		FileUtils.copyFile(screenshotFile, screenPath);
		return screenshotPath;
	}

	// Method to generate unique random id
	public static String generateUniqueID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // we have to specify the format
		Calendar cal = Calendar.getInstance();
		String uniqueid = sdf.format(cal.getTime());
		return uniqueid;
	}

	// Method to select option from dropdown
	public void SelectDropdownOption(WebElement dropdownelement, String option, String selectBy) {

		Select s = new Select(dropdownelement);
		if (selectBy.equalsIgnoreCase("VisibleText")) {
			s.selectByVisibleText(option);
		} else if (selectBy.equalsIgnoreCase("Value")) {
			s.selectByValue(option);
		} else if (selectBy.equalsIgnoreCase("Index")) {
			s.selectByIndex(Integer.parseInt(option));
		}

	}

	// Method to select checkbox
	public void selectCheckbox(WebElement element) {
		if (!element.isSelected())
			element.click();
	}

	// Method to perform double click
	public void DoubleClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}

	// Method to perform right click
	public void RightClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
	}

	// Method to wait -Java wait
	public void sleepTime(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Method to wait for element - Explicit wait
	public void WaitForElement(int seconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}
	
	public Properties appProperties() {
		return ReadDataFromPropertyFile.readProperties("Config.properties");
		
	}

}
