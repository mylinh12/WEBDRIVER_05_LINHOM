package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_08_JavascriptExecutor {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		// Firefox <=47 + selenium version 2.x.x
		driver = new FirefoxDriver();
		
		// Frirefox >=48 + selenium version 3.x.x
		// System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
		// driver = new FirefoxDriver();
		
		// Chrome
		// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();
		
		// IE
		// System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	/*So sánh tốc độ chạy script khi dùng Javascript với Built-in Selenium*/
	@Test
	public void TC_01_Case01_JavascriptExecutor() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://live.guru99.com/
		openURLByJS("http://live.guru99.com/");
		
		// Step 02 - Sử dụng JE để get domain của page ; Verify domain =  live.guru99.com
		String homePageDomain = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.guru99.com");
		
		// Step 03 - Sử dụng JE để get URL của page ; Verify URL =  http://live.guru99.com/
		String homePageUrl = (String) executeJSForBrowserElement("return document.URL;");
		Assert.assertEquals(homePageUrl, "http://live.guru99.com/");
		
		// Step 04 - Open MOBILE page (Sử dụng JE)
		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement(mobileLink);
		clickToElementByJS(mobileLink);
		
		// Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE) ; Hướng dẫn: sử dụng following-sibling
		WebElement samsungProduct = driver.findElement(By.xpath("//h2[a[@title='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
		highlightElement(samsungProduct);
		clickToElementByJS(samsungProduct);
		
		// Step 06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
		String samsungAddedMsg = (String) executeJSForBrowserElement("return document.documentElement.innerText;");
		Assert.assertTrue(samsungAddedMsg.contains("Samsung Galaxy was added to your shopping cart."));
		
		// Step 07 - Open PRIVACY POLICY page (Sử dụng JE); Verify title của page = Privacy Policy (Sử dụng JE)
		WebElement privacyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		highlightElement(privacyLink);
		clickToElementByJS(privacyLink);
		
		// verify title của Privacy Policy page (Sử dụng JE)
		String privacyPageTitle = (String) executeJSForBrowserElement("return document.title;");
		Assert.assertEquals(privacyPageTitle, "Privacy Policy");
		
		// Step 08 - Srcoll xuống cuối page
		scrollToBottomPage();
		
		// Step 09 - Verify dữ liệu 'WISHLIST_CNT' có hiển thị với chỉ 1 xpath: Hướng dẫn: sử dụng following-sibling
		WebElement wishlistTableContent = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		highlightElement(wishlistTableContent);
		Assert.assertTrue(wishlistTableContent.isDisplayed());
		
		// Step 10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE); Verify domain sau khi navigate = demo.guru99.com
		openURLByJS("http://demo.guru99.com/v4/");
		String demoPageDomain = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals(demoPageDomain, "demo.guru99.com");
		Thread.sleep(3000);
	}
	
	/*So sánh tốc độ chạy script khi dùng Javascript với Built-in Selenium*/
	@Test
	public void TC_01_Case02_BuiltInSelenium() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
		// Step 02 - Sử dụng API Built-in của selenium để get domain của page ; Verify domain =  live.guru99.com
		String homePageDomain = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.guru99.com");
		
		// Step 03 - Sử dụng API Built-in của selenium để get URL của page ; Verify URL =  http://live.guru99.com/
		String homePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homePageUrl, "http://live.guru99.com/");
		
		// Step 04 - Open MOBILE page (Sử dụng API Built-in của selenium)
		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement(mobileLink);
		mobileLink.click();
		
		// Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng API Built-in của selenium) ; Hướng dẫn: sử dụng following-sibling
		WebElement samsungProduct = driver.findElement(By.xpath("//h2[a[@title='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
		highlightElement(samsungProduct);
		samsungProduct.click();
		
		// Step 06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart. (Sử dụng API Built-in của selenium)
		String samsungAddedMsg = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertTrue(samsungAddedMsg.contains("Samsung Galaxy was added to your shopping cart."));
		
		// Step 07 - Open PRIVACY POLICY page (Sử dụng API Built-in của selenium); Verify title của page = Privacy Policy (Sử dụng API Built-in của selenium)
		WebElement privacyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		highlightElement(privacyLink);
		privacyLink.click();
		
		// Verify title của Privacy Policy page (Sử dụng API Built-in của selenium)
		String privacyPageTitle = driver.getTitle();
		Assert.assertEquals(privacyPageTitle, "Privacy Policy");
		
		// Step 08 - Srcoll xuống cuối page
		scrollToBottomPage();
		
		// Step 09 - Verify dữ liệu 'WISHLIST_CNT' có hiển thị với chỉ 1 xpath: Hướng dẫn: sử dụng following-sibling
		WebElement wishlistTableContent = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertTrue(wishlistTableContent.isDisplayed());
		
		// Step 10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng API Built-in của selenium); Verify domain sau khi navigate = demo.guru99.com
		driver.get("http://demo.guru99.com/v4/");		
		String demoPageDomain = (String) executeJSForBrowserElement("return document.domain;");
		Assert.assertEquals(demoPageDomain, "demo.guru99.com");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_02_RemoveAttribute() throws Exception {
		
		String firstName = "Automation", lastName = "Testing";
		// Step 01 - Truy cập vào trang: https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		
		// Step 02 - Remove thuộc tính disabled của field Last name; Switch qua iframe nếu có
		// switch qua iframe
		WebElement resultIframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(resultIframe);
		
		// Step 03 - Sendkey vào field Last name; Eg. Automation Testing
		// Input value into First name
		WebElement firstNameTextbox = driver.findElement(By.xpath("//input[@name='fname']"));
		highlightElement(firstNameTextbox);
		firstNameTextbox.sendKeys(firstName);
		
		// Input value into Last name
		WebElement lastNameTextbox = driver.findElement(By.xpath("//input[@name='lname']"));
		highlightElement(lastNameTextbox);
		removeAnyAttributeInDOM(lastNameTextbox, "disabled");
		lastNameTextbox.sendKeys(lastName);
		
		// Step 04 - Click Submit button
		WebElement submitBtn = driver.findElement(By.xpath("//input[@value='Submit']"));
		highlightElement(submitBtn);
		submitBtn.click();
		
		// Step 05 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field Lastname (Automation Testing)
		String successfulMsg = driver.findElement(By.xpath("//div[@class='w3-container w3-large w3-border']")).getText();
		Assert.assertTrue(successfulMsg.contains(firstName) && successfulMsg.contains(lastName));
		
		Thread.sleep(3000);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	/*Navigate any URL by Javascript: window.location = 'http://live.guru99.com/'*/
	public void openURLByJS(String url) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.location = '" + url + "'");
	}
	
	/*Highlight an element: arguments[0].style.border='6px groove red'*/
	public void highlightElement(WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove yellow'", element);
		Thread.sleep(2000);
	}
	
	/*Execute for Browser*/
	public Object executeJSForBrowserElement(String javasript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javasript);
	}
	
	/*Click To Element By Javascript: arguments[0].click();*/
	public Object clickToElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}
	
	// Remove attribute by Javascript: arguments[0].removeAttribute('" + attributeName + "');
	public Object removeAnyAttributeInDOM(WebElement element, String attributeName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attributeName + "');", element);
	}

	// scroll to bottom page by Javascript: window.scrollBy(0,document.body.scrollHeight)
	public Object scrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
}
