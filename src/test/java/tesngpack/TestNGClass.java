package tesngpack;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangehrm.framework.utilities.ReadDataFromExcel;
import com.orangehrm.framework.utilities.ReadDataFromPropertyFile;

public class TestNGClass {

//	public static void main(String[] args) {
//		new TestNGClass().loginMethod("ABC","1234");
//		new TestNGClass().test1();
//		new TestNGClass().logoutMethod();
//		
//		new TestNGClass().loginMethod("XYZ","5678");
//		new TestNGClass().test1();
//		new TestNGClass().logoutMethod();
//		
//		new TestNGClass().loginMethod("CBZ","0000");
//		new TestNGClass().test1();
//		new TestNGClass().logoutMethod();
//		
//		new TestNGClass().loginMethod("XYZ","5678");
//		new TestNGClass().test2();
//		new TestNGClass().logoutMethod();
//	}
	
	@BeforeSuite
	public void generateReport() {
		System.out.println("Reporting Started");
	}

	@Test(dataProvider = "loginData", priority = 2, groups = { "Rgeression", "Sanity" })
	public void test1(String username, String Password) {
		System.out.println("App URL == "+ReadDataFromPropertyFile.readProperties("Config.properties").getProperty("Url"));
		System.out.println("Test Case 1 Executed with " + username + " And " + Password);
	}
	
	@Test (dataProvider="loginData", priority=3, groups = {"Sanity"})
	public void test2(String username, String Password) {
		System.out.println("Test Case 2 Executed with "+username+" And "+Password);
	}
	
	@Test ( priority=4, enabled =true,groups = {"Rgeression"})
	public void test3() {
	System.out.println("Test Case 3 Executed ");
	}
	
	@Test (priority=1, dependsOnMethods= {"test3"},groups = {"Smoke"})
	public void test4() {
	System.out.println("Test Case 4 Executed ");
	}
	
	@BeforeMethod 
//	@Parameters("browser")
	public void loginMethod() {
	System.out.println("Login is Completed with the Browser : ");
	}
	
	@AfterMethod
	public void logoutMethod() {
	System.out.println("Logout is Completed");
	}
	
	@DataProvider (name ="loginData")
	public String[][] testData(){
		String [][] data = ReadDataFromExcel.getExcelData("TestData.xlsx", "Sheet1");
		return data;
	}
	
	
}

