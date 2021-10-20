package com.orangehrm.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.framework.utilities.ReadDataFromExcel;
import com.orangehrm.framework.webdriver.WebDriverClass;
import com.orangehrm.pages.ForgotPasswordPage;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class ApplicationTest extends WebDriverClass{
	
@Test (priority=1)	
public void VerifyApplicationTitle() throws IOException {
	initiateTestCaseReporting("Verify Application Title");
	LoginPage loginPage = LoginPage.getLoginPage();
	loginPage.launchApplication();
	loginPage.verifyApplicationTitle();
}

@Test (dataProvider="loginData",priority=2)
public void verifyApplicationLogin(String Username, String Password) throws IOException {
	initiateTestCaseReporting("Verify Application Login Feature");
	LoginPage loginPage = LoginPage.getLoginPage();
	HomePage homePage = HomePage.getHomePage();
	loginPage.launchApplication();
	loginPage.LoginIntoApplication(Username, Password);
	homePage.verifyWhetherAppLoginIsSuccessful();
}

@Test (dataProvider="loginData",priority=3)
public void verifyApplicationLogout(String Username, String Password) throws IOException {
	initiateTestCaseReporting("Verify Application Logout Feature");
	LoginPage loginPage = LoginPage.getLoginPage();
	HomePage homePage = HomePage.getHomePage();
	loginPage.launchApplication();
	loginPage.LoginIntoApplication(Username, Password);
	homePage.verifyApplicationLogout();
}

@Test(priority=4)
public void verifyForgotPasswordPage() throws IOException {
	initiateTestCaseReporting("Verify Forgot Pasword");
	LoginPage loginPage = LoginPage.getLoginPage();
	ForgotPasswordPage forgotPasswordPage = ForgotPasswordPage.getForgotPassword();
	loginPage.launchApplication();
	forgotPasswordPage.verifyForgotPasswordPageElements();
}

@DataProvider (name ="loginData")
public String[][] testData(){
	String [][] data = ReadDataFromExcel.getExcelData("TestData.xlsx", "Sheet1");
	return data;
}

}
