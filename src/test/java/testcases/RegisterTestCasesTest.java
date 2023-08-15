 package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTestCasesTest extends Base {
	
	public RegisterTestCasesTest() throws IOException { //constructor to load properties file from parent class
		super();     
	}
	
	
	public WebDriver driver;
	@BeforeMethod
	public void launch() {
		driver = launchbropenURL(prop.getProperty("browser"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test(priority = 1)
	public void mandetoryfeilds() {
	 	driver.findElement(By.xpath("//input[@name = 'firstname']")).sendKeys("Nsscdcl");
	 	driver.findElement(By.xpath("//input[@name = 'lastname']")).sendKeys("Nagpu");
	 	driver.findElement(By.xpath("//input[@name = 'telephone']")).sendKeys("1234123412");
	 	driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys(Utilities.generateEmailwithTimeStamp());
	 	driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("123456");
	 	driver.findElement(By.xpath("//input[@name = 'confirm']")).sendKeys("123456");	
	 	driver.findElement(By.xpath("//input[@name = 'agree']")).click();
	 	driver.findElement(By.xpath("//input[@value = 'Continue']")).click();
	 	
	 	String actualmessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
	 	Assert.assertEquals(actualmessage,"Your Account Has Been Created!","Account not created");
	 	}
	
	@Test(priority = 2)
	public void allfeilds() {
	 	driver.findElement(By.xpath("//input[@name = 'firstname']")).sendKeys("Nsscdcl");
	 	driver.findElement(By.xpath("//input[@name = 'lastname']")).sendKeys("Nagpur");
	 	driver.findElement(By.xpath("//input[@name = 'telephone']")).sendKeys("1234123412");
	 	driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys(Utilities.generateEmailwithTimeStamp());
	 	driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("123456");
	 	driver.findElement(By.xpath("//input[@name = 'confirm']")).sendKeys("123456");
	 	driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	 	driver.findElement(By.xpath("//input[@name = 'agree']")).click();
	 	driver.findElement(By.xpath("//input[@value = 'Continue']")).click();
	 	
	 	String actualmessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
	 	Assert.assertEquals(actualmessage,"Your Account Has Been Created!","Account not created");
	 	}
	
	@Test(priority = 3)
	public void existingemail() {
	 	driver.findElement(By.xpath("//input[@name = 'firstname']")).sendKeys("Nsscdcl");
	 	driver.findElement(By.xpath("//input[@name = 'lastname']")).sendKeys("Nagpur");
	 	driver.findElement(By.xpath("//input[@name = 'telephone']")).sendKeys("1234123412");
	 	driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("cocnsscdcl@gmail.com");
	 	driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("123456");
	 	driver.findElement(By.xpath("//input[@name = 'confirm']")).sendKeys("123456");
	 	driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	 	driver.findElement(By.xpath("//input[@name = 'agree']")).click();
	 	driver.findElement(By.xpath("//input[@value = 'Continue']")).click();
	 	
	 	String actualmessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
	 	Assert.assertEquals(actualmessage,"Warning: E-Mail Address is already registered!");
	 	}
	
	@Test(priority = 4)
	public void noinformation() {
	 	
	 	driver.findElement(By.xpath("//input[@value = 'Continue']")).click();
	 	
	 	String warning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
	 	Assert.assertEquals(warning,"Warning: You must agree to the Privacy Policy!");
	 	
	 	String fnamewarnig = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
	 	Assert.assertEquals(fnamewarnig, "First Name must be between 1 and 32 characters!");
	 	
	 	String lnamewarnig = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
	 	Assert.assertEquals(lnamewarnig, "Last Name must be between 1 and 32 characters!");
	 	
	 	String emailwarnig = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
	 	Assert.assertEquals(emailwarnig, "E-Mail Address does not appear to be valid!");
	 	

	 	String telephonewarnig = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
	 	Assert.assertEquals(telephonewarnig, "Telephone must be between 3 and 32 characters!");
	 	

	 	String passwarnig = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
	 	Assert.assertEquals(passwarnig, "Password must be between 4 and 20 characters!");
	 	
	 	}
	
	@AfterMethod()
	public void closebr() {
	driver.quit();
	
	}

}
