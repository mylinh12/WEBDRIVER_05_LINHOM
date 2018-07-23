package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebElement_WebBrowser {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
//		Firefox <=47 + selenium version 2.x.x
		driver = new FirefoxDriver();
		
//		Frirefox >=48 + selenium version 3.x.x
//		System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
//		Chrome
//		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//		driver = new ChromeDriver();
		
//		IE
//		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
		
	}

	@Test
	public void TC_01() throws InterruptedException {
		System.out.println("Check status of <Email/ Age (Under 18)/ Education> fields are displaying whether on page or not");
		
		// Define variables
		WebElement email, age, education;
		boolean isEmamilDisplayed = false, isAgeDisplayed = false, isEducationDisplayed = false;
		
		// Locator of elements
		email = driver.findElement(By.xpath("//input[@id='mail']"));		
		age = driver.findElement(By.xpath("//input[@id='under_18']"));
		education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		
		// Check elements display
		isEmamilDisplayed = email.isDisplayed();
		isAgeDisplayed = age.isDisplayed();
		isEducationDisplayed = education.isDisplayed();
		
		// If elememnts are displaying, user inputs valid value into element fields
		if((isEmamilDisplayed == true) && (isAgeDisplayed== true) && (isEducationDisplayed == true)) {
			email.sendKeys("Automation Testing");
			Thread.sleep(3000);
			
			age.click();
			Thread.sleep(3000);
			
			education.sendKeys("Automation Testing");
			Thread.sleep(3000);
		}
	}
	
	@Test
	public void TC_02() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
