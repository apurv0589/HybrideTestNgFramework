package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int implicit_wait_time =15; // public static to access variable with class name
	public static final int page_load_time =12;
	
	public static String generateEmailwithTimeStamp() {
		Date date =new Date();
		String sdate = date.toString().replace(" ","_").replace(":", "_");
		return "nsscdclnagpur"+sdate+"@gmail.com";
 	}
	
	public static Object[][] getdatafromsheet(String sheetname) throws IOException {
		
		String filepath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TestData.xlsx";
		File exlf = new File(filepath);
		FileInputStream fis = new FileInputStream(exlf); 
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		
		int rows = sheet.getLastRowNum();
		int colms = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][colms];
		
		for(int r=0;r<rows;r++) {
			XSSFRow row = sheet.getRow(r+1);
			
			for(int c=0;c<colms;c++) {
				XSSFCell cell = row.getCell(c);
				CellType celltype =cell.getCellType();
				switch(celltype) {
				case NUMERIC:
					data[r][c] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case STRING:
					data[r][c] = cell.getStringCellValue();
					
					break;
				case BOOLEAN:
					data[r][c] = cell.getBooleanCellValue();
					
					break;
				}
			}
		}
		return data;
		
		
	}
	
	public static String capturescreenshot(WebDriver driver, String testname) {
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testname+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}

		
}
