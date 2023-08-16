package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

// Test class for search functionality test cases
//Detail comment is added again please look now
public class SearchTest extends Base {
	public SearchTest() throws IOException {//constructor to load properties file from parent constructor
		super();
	}
	
  public WebDriver driver;
  
  @BeforeMethod
  public void setup() {
	  driver = launchbropenURL(prop.getProperty("browser"));
  }
  
  @Test(priority =1)
  public void searchvalidproduct() {
	  driver.findElement(By.xpath("//input[@name='search']")).sendKeys("HP");
	  driver.findElement(By.xpath("//button[@class = 'btn btn-default btn-lg']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
  }
  
  
  @Test(priority =2)
  public void searchinvaidproduct() {
	  driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Xiomi");
	  driver.findElement(By.xpath("//button[@class = 'btn btn-default btn-lg']")).click();
	  String msg = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
	  
	  Assert.assertEquals(msg, "Abs There is no product that matches the search criteria.");
  }
  
  @Test(priority =3, dependsOnMethods= {"searchinvaidproduct"})
  public void searchwithoutproduct() {
	  driver.findElement(By.xpath("//input[@name='search']")).sendKeys("");
	  driver.findElement(By.xpath("//button[@class = 'btn btn-default btn-lg']")).click();
	  String msg = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
	  
	  Assert.assertEquals(msg, "There is no product that matches the search criteria.");
  }
    
  @AfterMethod
  public void close() {
	  driver.quit();
  }
  
}
