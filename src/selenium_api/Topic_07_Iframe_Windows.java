package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Iframe_Windows {
	WebDriver driver;
	WebDriverWait wait;
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
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() throws Exception {

		// Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
		driver.get("http://www.hdfcbank.com/");

		/*-------------- ISSUE 01 at Step 02: Handle an element display (sometime it displays, sometime it does not display -------------*/
		// Step 02 - Close popup nếu có hiển thị (switch qua iframe nếu có) - F5 (refresh page) nhiều lần thì sẽ xuất hiện popup
		// Vì cái popup này, lúc thì nó hiển thị, lúc thì ko, nên mình phải khai báo nó dạng <List> để tránh bị throw exception khi ko tìm được popup.
		List<WebElement> notificationIframes = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("Number elements: " + notificationIframes.size());

		if (notificationIframes.size() > 0) {
			// Vì 'close popup' icon thuộc iframe, do đó phải switch qua iframe trước khi thao tác được với 'close popup' icon
			driver.switchTo().frame(notificationIframes.get(0));

			// click 'close popup' icon)
			WebElement closePopup = driver.findElement(By.xpath("//div[@id='div-close']"));
			JavascriptExecutor jsClosePopup = (JavascriptExecutor) driver;
			Thread.sleep(3000);
			jsClosePopup.executeScript("arguments[0].click();", closePopup);
			System.out.println("Close popup!");

			/*-------------- ISSUE 01 at Step 02: switch between 2 iframes (we must switch to defaultContent (Top Window) before) -------------*/
			// Switch to Top Windows
			driver.switchTo().defaultContent();
		}

		/*-------------- ISSUE 02 at Step 03: Handle dynamic iframe (id: it is random once page reloads --------------*/
		// Step 03 - Verify đoạn text được hiển thị: What are you looking for? (switch qua iframe nếu có)
		WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingForIframe);

		// check text displays
		String lookingForText = driver.findElement(By.xpath("//span[text()='What are you looking for?']")).getText();
		Assert.assertEquals(lookingForText, "What are you looking for?");

		// Switch to Top Windows
		driver.switchTo().defaultContent();

		// Step 04: Verify banner image được hiển thị (switch qua iframe nếu có) và Verify banner có đúng 6 images
		WebElement bannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));

		// Switch to banner iframe
		driver.switchTo().frame(bannerIframe);

		// Verify banner image có đúng 6 cái
		By bannerImageXpath = By.xpath("//div[@id='productcontainer']//img");
		List<WebElement> bannerImages = driver.findElements(bannerImageXpath);
		int numberOfImageSize = bannerImages.size();
		Assert.assertEquals(numberOfImageSize, 6);

		// check all image banners are displayed on DOM
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImageXpath));

		// Step 05 - Verify flipper banner được hiển thị và có 8 items
		driver.switchTo().defaultContent();
		List<WebElement> bannerFlipperImages = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		int numberOfFlipperImageSize = bannerFlipperImages.size();
		Assert.assertEquals(numberOfFlipperImageSize, 8);

		// check all flipper banners are displayed trong UI
		// For-each
		int i = 0;	
		for (WebElement image : bannerFlipperImages) {
			i++;
			Assert.assertTrue(image.isDisplayed());
			System.out.println("Image [" + i + "] is displayed!");
		}
		
		// For
		for (int x=0; x < bannerFlipperImages.size(); x++) {
			Assert.assertTrue(bannerFlipperImages.get(x).isDisplayed());
			System.out.println("Image [ 0" + x + "] is displayed!");
		}

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
