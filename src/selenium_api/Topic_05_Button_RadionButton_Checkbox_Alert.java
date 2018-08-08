package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_Button_RadionButton_Checkbox_Alert {
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
	public void TC_01_Button() throws Exception {
		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		//Step 02 - Click vào link My Account dưới footer (dùng hàm click của Selenium)
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		//Step 03 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/login/
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
		// Step 04 - Click vào button CREATE AN ACCOUNT (Sử dụng Javascript Executor code)
		WebElement createAnAccountBtn = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		clickElementByJavaScript(createAnAccountBtn);
		
		// Step 05 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/create/
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		
		Thread.sleep(4000);
	}
	
	@Test
	public void TC_02_Checkbox() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/radios
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		// Step 02 - Cick vào checkbox: Dual-zone air conditioning (Thẻ input ko được sử dụng thuộc tính id)
		
		/* Step 02: Issue 1
		 * Ở một số trang web, khi dùng thẻ <input> để click 
		 * --> sẽ bị lỗi 'element not visible' => tức là ko tìm thấy thẻ trên UI
		 * Mặc dù thẻ <input> có xuất hiện trong DOM.
		 
		WebElement dualzoneChb = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		dualzoneChb.click();
		*/
		
		/* Step 02: Issue 2
		 * Ở một số trang web, khi dùng thẻ <lablel> để click, tuy click được.
		 * Nhưng thẻ <label> lại ko dùng được hàm isSlected() để kiểm tra checkbox đó đã click được hay chưa 
		 * --> sẽ bị lỗi 'expected [true] but found [false]'.
		 
		 WebElement dualzoneChb = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']"));
		 dualzoneChb.click();
		 */
		
		/* Step 02: Giải quyết 2 issues trên bằng cách cick vào thẻ <input> thông qua Javascript */
		WebElement dualzoneChb = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		clickElementByJavaScript(dualzoneChb);
		Thread.sleep(3000);
		
		// Step 03 - Kiểm tra checkbox đó đã chọn
		Assert.assertTrue(dualzoneChb.isSelected());
		Thread.sleep(3000);
		
		// Step 04 - Sau khi checkbox đã được chọn - deselect nó và kiểm tra nó chưa được chọn
		/* Tại Step 04, để ko bị các issues của Step 02, thì hàm uncheckTheCheckbox,
		 * tại bước click(), thì cũng phải dùng click thông qua Javascript luôn nha*/
		uncheckTheCheckbox(dualzoneChb);
		Assert.assertFalse(dualzoneChb.isSelected());
		Thread.sleep(3000);
		
	}
	
	@Test
	public void TC_03_RadionButton() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/radios
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		// Step 02 - Cick vào radiobutton:  2.0 Petrol, 147kW (Thẻ input ko được sử dụng thuộc tính id)
		WebElement p20petrol147kWRdi=driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		clickElementByJavaScript(p20petrol147kWRdi);
		Thread.sleep(3000);
		
		// Step 03 - Kiểm tra radio button đó đã chọn hay chưa/ nếu chưa chọn lại
		// uncheck một radio button bằng cách click chọn radio button khác
		WebElement precedingSiblingElement=driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/../../li/label[text()='1.8 Petrol, 118kW']/preceding-sibling::input"));
		uncheckTheRadioButton(p20petrol147kWRdi, precedingSiblingElement);
		Assert.assertFalse(p20petrol147kWRdi.isSelected());
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_04_AcceptAlert() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("http://daominhdam.890m.com/");
		
		// Step 02 - Click vào button: Click for JS Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Thread.sleep(3000);
		
		// Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
		/*Lúc này, browser sẽ hiện một cái Alert pop-up, mình phải khai báo đối tượng Alert*/
		Alert jsAlert = driver.switchTo().alert();
		String contextOfAlert = jsAlert.getText();
		System.out.println("PRINT: " + contextOfAlert);
		Assert.assertEquals(contextOfAlert, "I am a JS Alert");
		Thread.sleep(3000);
		
		// Step 04 - Accept alert
		/*Click OK thông qua hàm accept()*/
		jsAlert.accept();
		Thread.sleep(3000);
		
		// Verify message hiển thị tại Result là:  You clicked an alert successfully
		WebElement resultAlert = driver.findElement(By.xpath("//p[@id='result']"));
		System.out.println("PRINT: " + resultAlert.getText());
		Assert.assertEquals(resultAlert.getText(), "You clicked an alert successfully");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_05_CancelAlert() throws Exception {
		// Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("http://daominhdam.890m.com/");

		// Step 02 - Click vào button: Click for JS Confirm
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Thread.sleep(3000);

		// Step 03 - Verify message hiển thị trong alert là: I am a JS Confirm
		Alert jsConfirmAlert = driver.switchTo().alert();
		String contextOfAlert = jsConfirmAlert.getText();
		System.out.println("PRINT: " + contextOfAlert);
		Assert.assertEquals(contextOfAlert, "I am a JS Confirm");
		Thread.sleep(3000);

		// Step 04 - Cancel alert
		/* Click Cancel thông qua hàm dismiss() */
		jsConfirmAlert.dismiss();
		Thread.sleep(3000);

		// Verify message hiển thị tại Result là: You clicked: Cancel
		WebElement resultAlert = driver.findElement(By.xpath("//p[@id='result']"));
		System.out.println("PRINT: " + resultAlert.getText());
		Assert.assertEquals(resultAlert.getText(), "You clicked: Cancel");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_06_PromptAlert() throws Exception {
		
		String name = "Automation Testing";
		
		// Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("http://daominhdam.890m.com/");

		// Step 02 - Click vào button: Click for JS Prompt
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Thread.sleep(3000);

		// Step 03 - Verify message hiển thị trong alert là: I am a JS prompt
		Alert jsPromptAlert = driver.switchTo().alert();
		String contextOfAlert = jsPromptAlert.getText();
		System.out.println("PRINT: " + contextOfAlert);
		Assert.assertEquals(contextOfAlert, "I am a JS prompt");
		Thread.sleep(3000);

		// Step 04 - Nhập vào text bất kì (daominhdam) và verify message hiển thị tại Result là: You entered: daominhdam
		/* nhập bằng hàm sendKeys() */
		jsPromptAlert.sendKeys(name);
		Thread.sleep(3000);
		
		// Click OK
		jsPromptAlert.accept();
		Thread.sleep(3000);

		// Verify message hiển thị tại Result là: You clicked: Cancel
		WebElement resultAlert = driver.findElement(By.xpath("//p[@id='result']"));
		System.out.println("PRINT: " + resultAlert.getText());
		Assert.assertEquals(resultAlert.getText(), "You entered: " + name);
		Thread.sleep(3000);
	}

	// Click an element by Javascript
	/*hồi trước mình click() thông qua selenium --> element.click();
	 * Bây giờ, mình có thể click element thông qua Javascript, bằng cách truyền vào chuỗi "arguments[0].click();"
	 * */
	public void clickElementByJavaScript(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}
	
	// Uncheck a checkbox element
	public void uncheckTheCheckbox(WebElement element) {
		if(element.isSelected()) {
			
			/* Cách 1 - click thông qua Selinium: 
			 * element.click();
			 * */
			
			/* Cách 2 - click thông qua Javascript*/
			clickElementByJavaScript(element);
		}
	}
	
	// Uncheck a radio button element
	public void uncheckTheRadioButton(WebElement element, WebElement precedingSiblingElement) {
		if (element.isSelected()) {

			/*
			 * Cách 1 - click thông qua Selinium: element.click();
			 */

			/* Cách 2 - click thông qua Javascript */
			clickElementByJavaScript(precedingSiblingElement);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
