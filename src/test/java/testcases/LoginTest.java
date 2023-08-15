package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.elementpages.Accountpage;
import com.tutorialsninja.qa.elementpages.Homepage;
import com.tutorialsninja.qa.elementpages.Loginpage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	public LoginTest() throws IOException {//constructor to load properties file from parent constructor
		super();
	}
	
	public WebDriver driver;
	
	//-----------------------------------DataDriver DataSupplier for test on multiple data-------------------------------//
	@DataProvider(name="ValidCredentialsSuppliers")
	public Object[][] validcredprovider() throws IOException {
		/*Object[][] data = {{"cocnsscdcl@gmail.com","Happy@555"},
				{"cocnsscdcl@gmail.com","Happy@555"},
				{"cocnsscdcl@gmail.com","Happy@555"}};
		return data;*/
		
		Object[][] data1 = Utilities.getdatafromsheet("Login");
		return data1;
	}
	
	//-------------------------------------------------------------------------------------------------------------------//
	@BeforeMethod
	public void startup() {
		driver = launchbropenURL(prop.getProperty("browser"));
		//-------from Homepage.java-------//
		Homepage homepage =new Homepage(driver);
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		homepage.clickonmyaccount();
		
		//driver.findElement(By.linkText("Login")).click();
		homepage.clickonlogin();
	}
	
	//-------------------------------------------------------------------------------------------------------------------//
	@Test(priority =1,dataProvider="ValidCredentialsSuppliers")
	public void verifyLoginWithValidCredentials(String email, String Pass) {		
		//driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		//driver.get("http://tutorialsninja.com/demo/");
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Login")).click();
		//------Common code has been move to one method to avoid repeated code annoted with before metho---------
		//===================From Loginpage.java===========//
		Loginpage loginpage = new Loginpage(driver);
		loginpage.enterid(email);
		//driver.findElement(By.id("input-email")).sendKeys(email);
		loginpage.enterpas(Pass);
		//driver.findElement(By.id("input-password")).sendKeys(Pass);
		loginpage.clickloginbutton();
		//driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		
		
		Accountpage accountpage = new Accountpage(driver);
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		Assert.assertTrue(accountpage.getdisplaystatusofmsg());
		//driver.quit();---------move to method annoted with after method
	}
	//-------------------------------------------------------------------------------------------------------------------//
	@Test(priority =2)
	public void verifyLoginWithInvalidCredentials() {	
		//driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		//driver.get("http://tutorialsninja.com/demo/");
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("123@555");
		
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualmessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedmessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualmessage.contains(expectedmessage),"Expected message not displayed");
		//driver.quit();
	}
	//-------------------------------------------------------------------------------------------------------------------//
	@Test(priority =3)
	public void verifyLoginWithValidInvalidpass() {	
		//driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		//driver.get("http://tutorialsninja.com/demo/");
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("cocnsscdcl@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("abc@123");
		
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualmessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedmessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualmessage.contains(expectedmessage),"Expected message not displayed");
		//driver.quit();
	}
	//-------------------------------------------------------------------------------------------------------------------//	
	@Test(priority = 4)
	public void verifyLoginWithInvalidValidpass() {	
		//driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		//driver.get("http://tutorialsninja.com/demo/");
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("Happy@555");
		
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualmessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedmessage = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualmessage.contains(expectedmessage),"Expected message not displayed");
		//driver.quit();
	}
	//-------------------------------------------------------------------------------------------------------------------//	
		@Test(priority = 5)
		public void verifyLoginWithNoidpass() {	
			//driver = new ChromeDriver();
			//driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
			//driver.get("http://tutorialsninja.com/demo/");
			//driver.findElement(By.xpath("//span[text()='My Account']")).click();
			//driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.id("input-email")).sendKeys("");
			driver.findElement(By.id("input-password")).sendKeys("");
			
			driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
			String actualmessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedmessage = "Warning: No match for E-Mail Address and/or Password.";
			
			Assert.assertTrue(actualmessage.contains(expectedmessage),"Expected message not displayed");
			//driver.quit();
		}
	//-------------------------------------------------------------------------------------------------------------------//	
	
	//-------------------------------------------------------------------------------------------------------------------//
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	

}
