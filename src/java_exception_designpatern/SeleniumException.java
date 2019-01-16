package java_exception_designpatern;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class SeleniumException {

	WebDriver driver;
	WebDriverWait wait;

	@Test
	public void TC_01_NoSuchElement() {
		driver = new FirefoxDriver();
		
		driver.get("https://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement myAccount = driver.findElement(By.xpath("//a[text()='Account']"));
		myAccount.click();
		
	}
	
	@Test
	public void TC_02_StaleElementReferenceException() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Panel1']")));
		
		// Before page RENDER
		WebElement dataNotSelected = driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals("No Selected Dates to display.", dataNotSelected.getText().trim());
		WebElement today = driver.findElement(By.xpath("//a[text()='2']"));
		today.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='2']")));
		
		//After page RENDER again
		Assert.assertEquals("Wednesday, May 02, 2018", dataNotSelected.getText().trim());
	}
	
	@Test
	public void TC_03_ElementNotVisibleException() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		
		driver.get("https://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Before page RENDER
		WebElement myAccount = driver.findElement(By.xpath("//div[@class='page-header-container']//a[text()='My Account']"));
		myAccount.click();
		
		driver.quit();
	}
	
	@Test
	public void TC_04_NoAlertPresentException() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Alert alert=driver.switchTo().alert();
		alert.accept();
		
	}
	
	@Test
	public void TC_05_InvalidSelectorException() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Xpath sai cú pháp
		WebElement jobDropdown = driver.findElement(By.xpath("select[id, 'job2']"));
		
	}
	
	@AfterTest
	public void quit() {
		driver.quit();
	}
}
