package selenium_testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_11_TestNG_07_DataProvider {
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod() {
		driver =new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "loginToSystem")
	public void TC_01_LoginAccount(String username, String password) {
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	}
	
	@Test(dataProvider = "newCustomer")
	public void TC_02_NewCustomer(String username, String password) {
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	}

	@DataProvider
	public Object[][] loginToSystem() {
		return new Object[][] { 
			new Object[] { "automationvalid_01@gmail.com", "111111" },
			new Object[] { "automationvalid_02@gmail.com", "111111" }, 
			new Object[] { "automationvalid_03@gmail.com", "111111"}
			};
	}

	@DataProvider
	public Object[][] newCustomer() {
		return new Object[][] { 
			new Object[] { "automationvalid_02@gmail.com", "111111" },
			new Object[] { "automationvalid_05@gmail.com", "111111" }, 
			new Object[] { "automationvalid_06@gmail.com", "111111"}
			};
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
