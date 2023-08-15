package com.tutorialsninja.qa.elementpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Accountpage {
	WebDriver driver;
	
	@FindBy(linkText="Edit your account information")
	private WebElement loginsuccessmsg;
	
	public Accountpage(WebDriver Driver) {
		this.driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//Actions
	public boolean getdisplaystatusofmsg() {
		boolean displaystatus = loginsuccessmsg.isDisplayed();
		return displaystatus;
	}
	
}
