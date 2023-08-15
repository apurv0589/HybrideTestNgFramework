package com.tutorialsninja.qa.elementpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	WebDriver driver;
	
	//objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myaccountdropmenu;
	
	@FindBy(linkText="Login")
	private WebElement longinlink;
	
	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//actions
	
	public void clickonmyaccount() {
		myaccountdropmenu.click();
	}
	
	public void clickonlogin() {
		longinlink.click();
	}
 
}
