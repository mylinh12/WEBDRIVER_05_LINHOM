package selenium_testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_11_TestNG_02_BeforeClass {
	
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver =new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		
	}
	
	@Test
	public void TC_01_checkUrl() {
		String loginUrl = driver.getCurrentUrl();
		System.out.println("loginUrl = " + loginUrl);
		Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
	}
	
	@Test
	public void TC_02_checkTitle() {
		String loginTitle = driver.getTitle();
		System.out.println("loginTitle = " + loginTitle);
		Assert.assertEquals(loginTitle, "Customer Login");
	}

	@AfterMethod
	public void afterMethod() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
