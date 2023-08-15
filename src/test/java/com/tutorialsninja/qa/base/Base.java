package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	
	public Base() throws IOException { //constructor to load properties file in child class
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis = new FileInputStream(propfile);
		prop.load(fis);
	}
	
	public WebDriver launchbropenURL(String browser) {
		
	if (browser.equalsIgnoreCase("chrome")){
		driver = new ChromeDriver();	
	}
	else if (browser.equalsIgnoreCase("firefox")){
		driver = new FirefoxDriver();	
	}
	else if (browser.equalsIgnoreCase("edge")){
		driver = new EdgeDriver();	
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicit_wait_time));//static final variable
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.page_load_time));
	driver.get(prop.getProperty("url"));
	return driver;
 }
}
