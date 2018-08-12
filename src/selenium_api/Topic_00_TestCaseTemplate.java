package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class Topic_00_TestCaseTemplate {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		// Firefox <=47 + selenium version 2.x.x
		// driver = new FirefoxDriver();
		
		// Frirefox >=48 + selenium version 3.x.x
		// System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
		// driver = new FirefoxDriver();
		
		// Chrome
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// IE
		// System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() throws Exception {
		
		driver.get("http://live.guru99.com/");
		
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_02() throws Exception {
		
		driver.get("http://live.guru99.com/");
		
		Thread.sleep(3000);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
