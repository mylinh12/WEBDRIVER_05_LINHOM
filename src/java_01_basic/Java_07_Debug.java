package java_01_basic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Java_07_Debug {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() {
		String homePageTitle = driver.getTitle();
		System.out.println(homePageTitle);
		Assert.assertEquals(homePageTitle, "Home page");
	}

	@Test
	public void TC_02() {
		String homePageUrl = driver.getCurrentUrl();
		System.out.println(homePageUrl);
		Assert.assertEquals(homePageUrl, "http://live.guru99.com/");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
