package java_01_basic;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Java_02_Variable {

	WebDriver driver;
	private String username, password, loginURL;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_Login_01_CreateUser() {
		loginURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("automationfc" + randomNumber() + "@gmail.com");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Access details to demo site.']")).isDisplayed());
		username = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
		
		System.out.println(loginURL);
	}

	@Test
	public void TC_Login_02_LoginToApplication() {
		driver.get(loginURL);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		WebElement welcomeMsg = driver.findElement(By.xpath("//marquee"));
		Assert.assertEquals("Welcome To Manager's Page of Guru99 Bank", welcomeMsg.getText());
		WebElement containUserID = driver.findElement(By.xpath("//td[text()='Manger Id : " + username + "']"));
		Assert.assertTrue(containUserID.isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		int number = rand.nextInt(999999) + 1;
		return number;
	}

}
