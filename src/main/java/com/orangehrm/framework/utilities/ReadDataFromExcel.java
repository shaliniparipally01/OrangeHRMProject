package com.orangehrm.framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {
	
	//This method will be used to read data from Excel
	public static String[][] getExcelData(String filename , String sheetname){
		
		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+filename);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet("Sheet1");
			XSSFRow row = sh.getRow(1);
			Cell cell= row.getCell(0);
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = row.getPhysicalNumberOfCells();
			
			data = new String [totalRows-1][totalColumns];
			for (int i=1; i<totalRows ;i++) {
				for(int j=0 ; j<totalColumns ;j++) {
					row = sh.getRow(i);
					cell = row.getCell(j);
					data[i-1][j] = cell.getStringCellValue();
				}
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return data;		
	}

}
