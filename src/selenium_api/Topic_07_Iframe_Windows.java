package selenium_api;

import java.util.List;
import java.util.Set;
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
		
		// Explicit Wait
		wait = new WebDriverWait(driver, 10);
		
		// Implicit Wait / hay còn gọi là Global Wait (wait toàn cục) => cái Global này sẽ ảnh hưởng đến 2 hàm findElement và findElements.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() throws Exception {

		// Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
		driver.get("http://www.hdfcbank.com/");

		/*-------------- ISSUE 01 at Step 02: Handle an element display (sometime it displays, sometime it does not display -------------*/
		// Step 02 - Close popup nếu có hiển thị (switch qua iframe nếu có) - F5 (refresh page) nhiều lần thì sẽ xuất hiện popup
		// Vì cái popup này, lúc thì nó hiển thị, lúc thì ko, nên mình phải khai báo nó dạng <List> để tránh bị throw exception khi ko tìm được popup.
		overrideGlobalWait(15);
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
		overrideGlobalWait(30);

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
	
	/*Case 01: switch between 2 windows or 2 tabs via GUID*/
	@Test
	public void TC_02_Windows_CASE01() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("http://daominhdam.890m.com/");
		
		// Get GUID of current page (parent page), thường thì GUID có dạng: DBE89D95-81B7-412E-A442-7CA3B78BBF09
		String parentGUID = driver.getWindowHandle();
		System.out.println("Parent GUID = " + parentGUID);
		System.out.println("Title before = " + driver.getTitle());
		Thread.sleep(3000);
		
		// Step 02 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
		// Switch to new window/ tab
		switchToChildWindowByGUID(parentGUID);
		
		// Step 03 - Kiểm tra title của window mới = Google
		String googleTitle = driver.getTitle();
		System.out.println("Title after = " + googleTitle);
		Assert.assertEquals(googleTitle, "Google");
		
		// Step 04 - Close window mới
		closeAllWithoutParentWindows(parentGUID);

		// Step 05 - Verify switch to parent window success
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
		Thread.sleep(3000);
		
	}
	
	/*Case 02: switch between greater 2 windows or greater 2 tabs via Title*/
	@Test
	public void TC_02_Windows_CASE02() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("http://daominhdam.890m.com/");
		
		// Get GUID of current page (parent page), thường thì GUID có dạng: DBE89D95-81B7-412E-A442-7CA3B78BBF09
		String parentGUID = driver.getWindowHandle();
		System.out.println("Parent GUID = " + parentGUID);
		System.out.println("Title before : " + driver.getTitle());
		Thread.sleep(3000);
		
		// Step 02 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
		// Switch to new window/ tab
		switchToWindowByTitle("Google");
		
		// Step 03 - Kiểm tra title của window mới = Google
		String googleTitle = driver.getTitle();
		System.out.println("Title after : " + googleTitle);
		Assert.assertEquals(googleTitle, "Google");
		
		// Step 04 - Close window mới
		closeAllWithoutParentWindows(parentGUID);
		
		// Step 05 - Verify switch to parent window success
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
		Thread.sleep(3000);
		
	}

	@Test
	public void TC_03_HdfcBankWindows() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
		driver.get("http://www.hdfcbank.com/");
		String parentGUID = driver.getWindowHandle();
		
		// Step 02 - Kiểm tra và close quảng cáo nếu có xuất hiện
		overrideGlobalWait(15);
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

			// Switch to Top Windows
			driver.switchTo().defaultContent();
		}
		
		overrideGlobalWait(30);
		
		// Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		Thread.sleep(3000);
		
		// Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		Thread.sleep(3000);
		
		// Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
		// switch to frame 
		WebElement footerFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(footerFrame);
		Thread.sleep(3000);
		
		// click Privacy Policy link (nằm trong frame)
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		
		// Switch qua tab mới
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		Thread.sleep(3000);
		
		// Step 06- Click CSR link on Privacy Policy page
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		
		// Step 07-08 - Close tất cả popup khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
		closeAllWithoutParentWindows(parentGUID);
		Thread.sleep(3000);
		Assert.assertEquals(driver.getTitle(), "HDFC Bank: Personal Banking Services");
		
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void afterClass() {
		 driver.quit();
	}
	
	/*Method: Switch to child Windows (only 2 windows)*/
	public void switchToChildWindowByGUID(String parentGUID) {
		
		// Get all current windows/ tabs
		Set <String> allWindows = driver.getWindowHandles();
		
		// Duyệt qua tất cả các windows/ tabs
		for (String runWindow : allWindows) {
			
			System.out.println("runWindow GUID = " + runWindow);
			
			// Nếu window/ tab ID nào mà khác với ParentGUID thì switch qua
			if(!runWindow.equals(parentGUID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	/*Method: Switch to child Windows (greater 2 windows and title of pages are unique)*/
	public void switchToWindowByTitle(String expectedTitle) {
		
		// Get all current windows/ tabs
		Set <String> allWindows = driver.getWindowHandles();
		
		// Duyệt qua tất cả các windows/ tabs
		for (String runWindow : allWindows) {
			
			System.out.println("runWindow GUID = " + runWindow);
			
			// switch vào từng windows trước
			driver.switchTo().window(runWindow);
			
			// Get ra title của window/ tab mà mình đã switch qua.
			String currentWindowTitle=driver.getTitle();
			
			// kiểm tra title mà mình đã get mà bằng với expected title mà mình truyền vào thì break khỏi vòng for.
			if(currentWindowTitle.equals(expectedTitle)) {
				
				// thoát khỏi vòng lặp for.
				break;
			}
		}
	}

	/*Method: Đóng tất cả các windows/ tabs ngoại trừ parent window*/	
	public boolean closeAllWithoutParentWindows(String parentGUID) {
	
		// Get all current windows/ tabs
		Set <String> allWindows = driver.getWindowHandles();
		
		// Duyệt qua tất cả các windows/ tabs
		for(String runWindow : allWindows) {
			
			System.out.println("runWindow GUID = " + runWindow);
			
			// Nếu window/ tab ID nào mà khác với ParentGUID thì:
			if(!runWindow.equals(parentGUID)) {
				
				// Switch qua window/ tab đó.
				driver.switchTo().window(runWindow);
				
				// Close window/ tabs đó.
				driver.close();
			}
		}
		
		// Switch về parent window
		driver.switchTo().window(parentGUID);
		
		// Kiểm tra xem có còn đúng 1 window hay ko
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	/* Method: Override Global Wait */
	public void overrideGlobalWait(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

}
