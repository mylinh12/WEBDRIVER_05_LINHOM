package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Textbox_TextArea_Dropdown {
	WebDriver driver;	
	String userName ="abc", pass, customerName, dob, address, city, state, pin, mobilePhoneNumber, email, password, customerId, newAddress, newCity;

	@BeforeClass
	public void beforeClass() {
		// Firefox <=47 + selenium version 2.x.x
		 driver = new FirefoxDriver();

		// Frirefox >=48 + selenium version 3.x.x
		// System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// Chrome
//		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//		driver = new ChromeDriver();

		// IE
		// System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// userName = "mngr94214";
		// pass = "Uqazedu";

		userName = "mngr145361";
		pass = "uzutebA";

		customerName = "MLinh";
		dob = "1970-01-01";
		address = "123 Nguyen Trai Street";
		city = "Ho Chi Minh";
		state = "Six";
		pin = "123456";
		mobilePhoneNumber = "0900123456";
		email = "mlinh_" + randomNumber() + "@mailinator.com";
		password = "uzutebAB";
		
		newAddress="456 Nguyen Trai";
		newCity="Thanh Pho HCM";

	}

	@Test
	public void TC_01() throws Exception {
		
		// step 1 - navigate url
		driver.get("http://daominhdam.890m.com/");

		// step 2 - checking <Job Role 01> dropdown list does not support multi-select
		Select jobRole01DropDownList = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Thread.sleep(3000);
		Assert.assertFalse(jobRole01DropDownList.isMultiple());

		// step 3 - select 'Automation Tester' value in dropdown list by selectByVisibleText method.
		jobRole01DropDownList.selectByVisibleText("Automation Tester");
		Thread.sleep(3000);
		// step 4 - verify the 'Automation Tester' option has been selected successfully
		Assert.assertEquals(jobRole01DropDownList.getFirstSelectedOption().getText(), "Automation Tester");

		// step 5 - select 'Manual Tester' value in dropdown list by selectByValue method.
		jobRole01DropDownList.selectByValue("manual");
		Thread.sleep(3000);
		// step 6 - verify the 'Manual Tester' option has been selected successfully
		Assert.assertEquals(jobRole01DropDownList.getFirstSelectedOption().getText(), "Manual Tester");

		// step 7 - select 'Mobile Tester' value in dropdown list by selectByIndex method.
		jobRole01DropDownList.selectByIndex(3);
		Thread.sleep(3000);
		// step 8 - verify the 'Mobile Tester' option has been selected successfully
		Assert.assertEquals(jobRole01DropDownList.getFirstSelectedOption().getText(), "Mobile Tester");

		// step 9 - checking how many options in <Job Role 01> dropdown list.
		Assert.assertEquals(jobRole01DropDownList.getOptions().size(), 5);
		
	}
	
	@Test
	public void TC_02() throws Exception {
		// The purpose of this case is verify the last item is number 19
		
		// step 1 - navigate url
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
//		// step 2 - select the last item (e.g: '19')
//		Select selectANumberDropDownList = new Select(driver.findElement(By.xpath("//select[@id='number']")));
//		selectANumberDropDownList.selectByVisibleText("19");
//		Thread.sleep(3000);
//		
//		// step 3 - verify the value of the last item is number 19
//		List<WebElement> optionList = driver.findElements(By.xpath("//select[@id='number']//option"));
//		Assert.assertEquals(optionList.get(optionList.size()-1).getText(), "19");
		
		/////////////////////////// investigate /////////////////////////
		// print all text list
		String inputText="19";
		Select selectANumberDropDownList = new Select(driver.findElement(By.xpath("//select[@id='number']")));
		List<WebElement> optionList = driver.findElements(By.xpath("//select[@id='number']//option"));
		
		// select "19" by selectByIndex method, because can not use selectByValue method
		for (int index = 0; index < optionList.size(); index++) {
			System.out.println("Option [" + index +"]: " + optionList.get(index).getText());
			if(optionList.get(index).getText().equalsIgnoreCase(inputText)) {
				selectANumberDropDownList.selectByIndex(index);
				break;
			}
		}
		
		Thread.sleep(3000);
		Assert.assertEquals(optionList.get(optionList.size()-1).getText(), "19");
	}
	
	@Test
	public void TC_03() throws Exception {
		driver.get("http://demo.guru99.com/v4");

		// step 2 - login username / password
		WebElement userTextbox = driver.findElement(By.xpath("//input[@name='uid']"));
		WebElement passTextbox = driver.findElement(By.xpath("//input[@name='password']"));
		userTextbox.sendKeys(userName);
		passTextbox.sendKeys(pass);
		Thread.sleep(6000);
		WebElement loginButton = driver.findElement(By.xpath("//input[@name='btnLogin']"));
		loginButton.click();
		Thread.sleep(5000);

		// step 3 - click New Customer link
		WebElement newCustomerLink = driver.findElement(By.xpath("//a[text()='New Customer']"));
		newCustomerLink.click();
		Thread.sleep(3000);

		// step 4 - input valid value into all fields
		WebElement customerNameTextbox = driver.findElement(By.xpath("//input[@name='name']"));
		customerNameTextbox.sendKeys(customerName);

		WebElement femaleRad = driver.findElement(By.xpath("//input[@value='f']"));
		femaleRad.click();

		WebElement dateOfBirth = driver.findElement(By.xpath("//input[@id='dob']"));
		removeAttributeInDOM(dateOfBirth, "type");
		Thread.sleep(5000);
		dateOfBirth.sendKeys(dob);

		WebElement addressTextArea = driver.findElement(By.xpath("//textarea[@name='addr']"));
		addressTextArea.sendKeys(address);

		WebElement cityTextbox = driver.findElement(By.xpath("//input[@name='city']"));
		cityTextbox.sendKeys(city);

		WebElement stateTextbox = driver.findElement(By.xpath("//input[@name='state']"));
		stateTextbox.sendKeys(state);

		WebElement pinTextbox = driver.findElement(By.xpath("//input[@name='pinno']"));
		pinTextbox.sendKeys(pin);

		WebElement mobilePhoneTextbox = driver.findElement(By.xpath("//input[@name='telephoneno']"));
		mobilePhoneTextbox.sendKeys(mobilePhoneNumber);

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='emailid']"));
		emailTextbox.sendKeys(email);

		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@name='password']"));
		passwordTextbox.sendKeys(password);

		WebElement submitBtn = driver.findElement(By.xpath("//input[@name='sub']"));
		submitBtn.click();
		Thread.sleep(3000);

		// VERIFY CUSTOMER CREATED SUCCESS MESSAGE
		WebElement createdSuccessMsg = driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"));
		Assert.assertTrue(createdSuccessMsg.isDisplayed());

		// step 5 - Get customer id
		customerId = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

		// VERIFY THE CREATED CUSTOMER INFORMATION
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), "female");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobilePhoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		// step 6 - Edit customer  // customerID = 112162
		WebElement editCustomerLink = driver.findElement(By.xpath("//a[text()='Edit Customer']"));
		editCustomerLink.click();
		Thread.sleep(3000);
		
		// <customer> field in Edit Customer page
		WebElement customerIdTextBox = driver.findElement(By.xpath("//input[@name='cusid']"));
		System.out.println("Customer ID: " + customerId);
		customerIdTextBox.sendKeys(customerId);
		
		// <Submit> button in Edit Customer page
		WebElement submit = driver.findElement(By.xpath("//input[@name='AccSubmit']"));
		submit.click();
		Thread.sleep(3000);
		
		// step 7 - Verify 2 fields <Customer Name> and <Address>
		WebElement customerNameTxtEditPage=driver.findElement(By.xpath("//input[@name='name']"));
		Assert.assertEquals(customerNameTxtEditPage.getAttribute("value"), customerName);
		
		WebElement addressTextAreaEditPage = driver.findElement(By.xpath("//textarea[@name='addr']"));
		Assert.assertEquals(addressTextAreaEditPage.getText(), address);
		
		// Edit value in <Address> fields
		addressTextAreaEditPage.clear();
		addressTextAreaEditPage.sendKeys(newAddress);
		
		// Edit value in <City> fields
		WebElement cityTxtEditPage=driver.findElement(By.xpath("//input[@name='city']"));
		cityTxtEditPage.clear();
		cityTxtEditPage.sendKeys(newCity);
		
		//click Submit
		WebElement submitBtnEditPage=driver.findElement(By.xpath("//input[@name='sub']"));
		submitBtnEditPage.click();
		Thread.sleep(3000);
		
		// Verify Success Message
		WebElement editCustomerSuccessMsg = driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']"));
		Assert.assertTrue(editCustomerSuccessMsg.isDisplayed());
		
		// Verify new address value has been updated successfully 
		WebElement addressAfterEditing=driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td"));
		Assert.assertEquals(addressAfterEditing.getText(), newAddress);
		
		// Verify new city value has been updated successfully 
		WebElement cityAfterEditing = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td"));
		Assert.assertEquals(cityAfterEditing.getText(), newCity);
	}

	public int randomNumber() {
		Random randomNumber = new Random();
		int number = randomNumber.nextInt(999999);
		System.out.println("Random number is: " + number);
		return number;
	}

	public void removeAttributeInDOM(WebElement element, String attributeName) {
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("arguments[0].removeAttribute('" + attributeName + "')", element);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
