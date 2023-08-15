package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReporter {
	
	public static ExtentReports generateExtentReport() throws IOException {
		ExtentReports extentreport = new ExtentReports();
		File extentreportfile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentreportfile);
		
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("TestResultsReport");
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentreport.attachReporter(sparkreporter);
		
		Properties prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis = new FileInputStream(propfile); 
		prop.load(fis);
		
		extentreport.setSystemInfo("Application URL:", prop.getProperty("url"));
		extentreport.setSystemInfo("Browser Name:", prop.getProperty("browser"));
		extentreport.setSystemInfo("Windows Version:", System.getProperty("os.name"));
		extentreport.setSystemInfo("java Version:", System.getProperty("java.version"));
		extentreport.setSystemInfo("User Name:", System.getProperty("user.name"));
		return extentreport;
	}

}
