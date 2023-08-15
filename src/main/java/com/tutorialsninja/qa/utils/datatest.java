package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class datatest {
public static Object[][] getdatafromexcel() throws IOException {
		
		File excelfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TestData.xlsx");
		FileInputStream fisExcel = new FileInputStream(excelfile);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int rows = sheet.getLastRowNum();
		int col = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][col];
		
		for(int r=0;r<rows;r++) {
			XSSFRow row = sheet.getRow(r=+1);
			
			for(int c=0;c<col;c++) {
				XSSFCell cell = row.getCell(c);
				
							
				switch(cell.getCellType()) {
				case STRING:
					data[r][c] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[r][c] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[r][c] = cell.getBooleanCellValue();
					
				}
			}
			
		}
		return data;		
	}
	
	public static void nmain(String[] args) throws IOException {
		getdatafromexcel();
	}
	
}
