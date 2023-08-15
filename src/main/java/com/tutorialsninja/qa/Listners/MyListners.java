package com.tutorialsninja.qa.Listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtendReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListners implements ITestListener {

	ExtentReports extentreport;
	ExtentTest reporttestname;
	String testname;
	@Override
	public void onStart(ITestContext context) {
		try {
			extentreport = ExtendReporter.generateExtentReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("Test Starts");
		
		}
	
	@Override
	public void onTestStart(ITestResult result) {
		testname = result.getName();
		reporttestname = extentreport.createTest(testname);
		reporttestname.log(Status.INFO, testname+"Started executing");
		System.out.println(testname+"Started executing");				
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//String testname = result.getName();
	     reporttestname.log(Status.PASS, testname+"Executed Successfuly");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//String testname = result.getName();
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.capturescreenshot(driver, testname);
		reporttestname.addScreenCaptureFromPath(destinationScreenshotPath);
		reporttestname.log(Status.INFO, result.getThrowable());
		reporttestname.log(Status.FAIL, testname + "failed to execute");
		}

	@Override
	public void onTestSkipped(ITestResult result) {
		//String testname = result.getName();
		reporttestname.log(Status.INFO, result.getThrowable());
		reporttestname.log(Status.SKIP, testname + "Skkiped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
		//to open extent report automatically after completing test execusion
		String extentreportpath = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentreportfile = new File(extentreportpath);
		try {
			Desktop.getDesktop().browse(extentreportfile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
	}

}
