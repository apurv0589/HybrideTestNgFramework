package com.tutorialsninja.qa.elementpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	WebDriver driver;
	
	//Objects
	@FindBy(id = "input-email")
	private WebElement id;
	
	@FindBy(id = "input-password")
	private WebElement pass;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement buttonlogin;
	
	public Loginpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void enterid(String emailtext) {
		id.sendKeys(emailtext);
	}
	
	public void enterpas(String passtext) {
		pass.sendKeys(passtext);
	}
	
	public void clickloginbutton() {
		buttonlogin.click();
	}

}
