package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_XpathCss {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_VerifyUrlAndTitle() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Check Url of My Account page");
		driver.navigate().back();
		String myAccountPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(myAccountPageUrl, "http://live.guru99.com/index.php/customer/account/login/");
		
		System.out.println("Check Url of Create Account page");
		driver.navigate().forward();
		String createPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(createPageUrl, "http://live.guru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_LoginEmpty() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		System.out.println("Check Email Error Message in case logging empty");
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");
		
		System.out.println("Check Password Error Message in case logging empty");
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");
	}
	
	@Test
	public void TC_03_LoginWithEmailInvalid() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		System.out.println("Check Email Error Message in case logging with email invalid");
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		System.out.println("Check Password Error Message in case logging with password incorrect");
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
