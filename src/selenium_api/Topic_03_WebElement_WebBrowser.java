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
		
		System.out.println("\n--------------- Check elements are displayed ---------------");
		if (isEmamilDisplayed == true) {
			email.sendKeys("Automation Testing");
			Thread.sleep(3000);
		}
		
		if (isAgeDisplayed == true) {
			age.click();
			Thread.sleep(3000);
		}

		if (isEducationDisplayed == true) {
			education.sendKeys("Automation Testing");
			Thread.sleep(3000);
		}
	}
	
	@Test
	public void TC_02() {
		
		
		// Define variables
		WebElement email, ageUnder18, education, jobRole01, interestsDevelopment, slider01, password,
				ageRadiobuttonIsDisabled, biography, jobRole02, interestsCheckboxIsDisabled, slider02;

		// Locator of elements
		email = driver.findElement(By.xpath("//input[@id='mail']"));
		ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		jobRole01 = driver.findElement(By.xpath("//select[@id='job1']"));
		interestsDevelopment = driver.findElement(By.xpath("//input[@id='development']"));
		slider01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		password = driver.findElement(By.xpath("//input[@id='password']"));
		ageRadiobuttonIsDisabled = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		biography = driver.findElement(By.xpath("//textarea[@id='bio']"));
		jobRole02 = driver.findElement(By.xpath("//select[@id='job2']"));
		interestsCheckboxIsDisabled = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		slider02 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		
		// Check elements are enabled
		System.out.println("\n\nCheck <Email/ Age (Under 18)/ Education/ Job Role 01/ Interests (Development)/ Slider 01/> Button is enabled");
		System.out.println("\n--------------- Check elements are enabled ---------------");
		
		if (email.isEnabled()) {
			System.out.println("<Email> field is enabled");
		} else {
			System.out.println("<Email> field is disable");
		}
		
		if (ageUnder18.isEnabled()) {
			System.out.println("<Age (Under 18)> field is enabled");
		} else {
			System.out.println("<Age (Under 18)> field is disable");
		}
		
		if (education.isEnabled()) {
			System.out.println("<Education> field is enabled");
		} else {
			System.out.println("<Education> field is disable");
		}
		
		if (jobRole01.isEnabled()) {
			System.out.println("<Job Role 01> field is enabled");
		} else {
			System.out.println("<Job Role 01> field is disable");
		}
		
		if (interestsDevelopment.isEnabled()) {
			System.out.println("<Interests - Development> field is enabled");
		} else {
			System.out.println("<Interests - Development> field is disable");
		}
		
		if (slider01.isEnabled()) {
			System.out.println("<Slider 01> field is enabled");
		} else {
			System.out.println("Slider> 01 field is disable");
		}
		
		// Check elements are disabled
		System.out.println("\n\nCheck <Password/ Age Radiobutton Is Disabled/ Biography/ Job Role 02/ Interests - Checkbox is disabled/ Slider 02/> Button is disable");
		System.out.println("\n--------------- Check elements are disabled ---------------");
		
		if (password.isEnabled() == false) {
			System.out.println("<Password> field is disable");
		} else {
			System.out.println("<Password> field is enabled");
		}
		
		if (ageRadiobuttonIsDisabled.isEnabled() == false) {
			System.out.println("<Age Radiobutton Is Disabled> field is disable");
		} else {
			System.out.println("<Age Radiobutton Is Disabled> field is enabled");
		}
		
		if (biography.isEnabled() == false) {
			System.out.println("<Biography> field is disable");
		} else {
			System.out.println("<Biography> field is enabled");
		}
		
		if (jobRole02.isEnabled() == false ) {
			System.out.println("<Job Role 02> field is disable");
		} else {
			System.out.println("<Job Role 02> field is enabled");
		}
		
		if (interestsCheckboxIsDisabled.isEnabled() == false) {
			System.out.println("<Interests - Checkbox is disabled> field is disable");
		} else {
			System.out.println("<Interests - Checkbox is disabled> field is enabled");
		}
		
		if (slider02.isEnabled() == false) {
			System.out.println("<Slider 02> field is disable");
		} else {
			System.out.println("<Slider 02> field is enabled");
		}
	}

	@Test
	public void TC_03() throws InterruptedException{
		
		// Define variables
		WebElement ageUnder18, interestsDevelopment;
		
		// Locators
		ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		interestsDevelopment = driver.findElement(By.xpath("//input[@id='development']"));
		
		// Action with elements
		ageUnder18.click();
		Thread.sleep(3000);
		
		interestsDevelopment.click();
		Thread.sleep(3000);
		
		if(ageUnder18.isSelected()==false) {
			ageUnder18.click();
			Thread.sleep(3000);
		}
		
		if(interestsDevelopment.isSelected()==false) {
			ageUnder18.click();
			Thread.sleep(3000);
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
