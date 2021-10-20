package com.orangehrm.framework.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class WebDriverClass {
	
	protected static WebDriver driver;
	public static ExtentReports extent = null;  // initialize report
	public static ExtentTest logger = null; // Logger is meant for print
	
	//To Launch the Browser Session
	@BeforeMethod
	@Parameters("browser")
	public void setupBrowser(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir") + "\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}	
		
		driver.manage().window().maximize();
	}
	
	//Method to share driver session with other classes
	public static WebDriver getDriver() {
		return driver;
	}
	
	//To Close the the Browser sessions
	@AfterMethod
	public void teardown() {
		driver.quit();
		extent.flush();
	}
	
	@BeforeSuite
	public void startReport() {
		ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(System.getProperty("user.dir") +"\\Reports\\AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);
	}
	
	//Method to start printing the events of test case
	public static void initiateTestCaseReporting(String testcasename) {
		logger=extent.createTest(testcasename);
	}
	
	public static ExtentTest getLogger() {
		return logger;
	}

}
