package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		
//		LINK TEXT --> use for only LINK element
		driver.findElement(By.linkText("My Account")).click();
		
//		ID --> attribute id
		driver.findElement(By.id("email")).sendKeys("");
		
//		CLASS --> attribute class
		driver.findElement(By.className("input-text required-entry validate-password")).sendKeys("");
		
//		NAME --> attribute name
		driver.findElement(By.name("send")).click();
		
//		CSS --> attribute id --> #
		System.out.println("Check Email Error Message in case logging empty");
		String emailErrorMsg = driver.findElement(By.cssSelector("#advice-required-entry-email")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");
		
//		CSS has a format LITTLE NEAR like the format of XPATH
		System.out.println("Check Password Error Message in case logging empty");
		String passwordErrorMsg = driver.findElement(By.cssSelector("div[id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");
		
//		TAGNAME --> Đếm xem trang login có bao nhiêu đường link
		List <WebElement> elements = driver.findElements(By.tagName("a"));
		int number = elements.size();
		System.out.println("number of tagname is: " + number);
	}
	
	@Test
	public void TC_03_LoginWithEmailInvalid() {
		
//		PARTIAL LINK TEXT  --> use for only LINK element
		driver.findElement(By.partialLinkText("My")).click();
		
//		XPATH --> //tagname[@attribute = 'value']
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
	
	@Test
	public void TC_05_CreateAnAccount() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Linh");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("M");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("O");
		
		String emailRandom = "MLinh" + ((int)(Math.random()*100) + 1) + "@wimsg.com";
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailRandom);
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pass@123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("pass@123");
		driver.findElement(By.xpath("//button[@class='button' and @title='Register']")).click();
		
		System.out.println("Check Registering Successfully message");
		String registeringSuccessfulMsg = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(registeringSuccessfulMsg, "Thank you for registering with Main Website Store.");
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		System.out.println("Check website navigate to hompage after logging out successfully");
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
