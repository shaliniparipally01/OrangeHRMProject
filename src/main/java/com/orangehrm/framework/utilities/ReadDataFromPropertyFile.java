package com.orangehrm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile {
	
	public static Properties readProperties(String filename) {
		Properties prop = null;
		FileInputStream fis = null;		
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+filename);
			prop= new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return prop;
	}

}
