package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_Wait {
	
	WebDriver driver;
	WebDriverWait waitExpicit;
	
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
		
		waitExpicit = new WebDriverWait(driver, 30);
		
		// driver.manage().window().maximize();
		
		
	}

	/*Giả lập TC fail*/
	@Test
	public void TC_01_Case01_ImplicitlyWait() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		// Step 02 - Define an implicit wait (If you set 2 seconds, test will fail)
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		// Step 03 - Click the start button
		WebElement startBtn = driver.findElement(By.xpath("//*[@id='start']/button"));
		startBtn.click();
		
		// Step 04 - Wait result text will appear
		// chỗ này là đang wait trong phạm vi thời gian set trong implicityWait của Step 2.
		
		// Step 05 - Check result text is "Hello World!"
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
	}
	
	/*Expected: TC will be pass*/
	@Test
	public void TC_01_Case02_ImplicitlyWait() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		
		// Step 02 - Define an implicit wait (If you set 2 seconds, test will fail)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Step 03 - Click the start button
		WebElement startBtn = driver.findElement(By.xpath("//*[@id='start']/button"));
		startBtn.click();
		
		// Step 04 - Wait result text will appear
		// chỗ này là đang wait trong phạm vi thời gian set trong implicityWait của Step 2.
		
		// Step 05 - Check result text is "Hello World!"
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
	}
	
	/*Expected:  TC will be pass because using: 
	 * 1. Có dùng ExplicitlyWait --> lỗi gì
	 * 2. Trường hợp DOM bị render lại --> thì element phải nhận dạng xpath lại
	 * 3. Khi nào dùng visibility / invisibility: Ajax loading icon nên dùng invisibility vì nó loading và biến mất rất là nhanh*/
	@Test
	public void TC_02_Case01_ExplicitlyWait() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
		WebElement dateTimePicker = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']"));
		waitExpicit.until(ExpectedConditions.visibilityOf(dateTimePicker));
		
		// Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
		WebElement dateSelectedBefore = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		String textBefore = dateSelectedBefore.getText();
		System.out.println("textBefore: " + textBefore);
		Assert.assertEquals(textBefore, "No Selected Dates to display.");
		
		// Step 04 - Chọn ngày hiện tại (VD: 09/09/2018) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
		WebElement today = driver.findElement(By.xpath("//a[text()='9']"));
		today.click();
		
		// Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility) Xpath: //div[@class='raDiv']
		By byAjaxLoadingIcon = By.xpath("//div[@class='raDiv']");
		waitExpicit.until(ExpectedConditions.invisibilityOfElementLocated(byAjaxLoadingIcon));
		
		// Step 06 - Wait cho selected date = 09 được visible ((sử dụng: visibility) Xpath: //*[contains(@class,'rcSelected')]//a[text()='09']
		waitExpicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")).isDisplayed());
		
		// Step 07 - Verify ngày đã chọn bằng = Sunday, September 09, 2018
		WebElement dateSelectedAfter = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		String textAfter = dateSelectedAfter.getText().trim();
		System.out.println("textAfter: " + textAfter);
		Assert.assertEquals(textAfter, "Sunday, September 09, 2018");
		
	}
	
	/*Giả lập TC Fail: 
	 * 1. ko dùng ExplicitlyWait --> lỗi gì
	 * 2. ko wait Ajax Loading xong --> lỗi gì*/
	@Test
	public void TC_02_Case02_ExplicitlyWait() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
		WebElement dateTimePicker = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']"));
		Assert.assertTrue(dateTimePicker.isDisplayed());
		
		// Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
		WebElement dateSelectedBefore = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		String textBefore = dateSelectedBefore.getText();
		System.out.println("textBefore: " + textBefore);
		Assert.assertEquals(textBefore, "No Selected Dates to display.");
		
		// Step 04 - Chọn ngày hiện tại (VD: 09/09/2018) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
		WebElement today = driver.findElement(By.xpath("//a[text()='9']"));
		today.click();

		// Giả định ko có bước Wait Ajax Loading tại Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility) Xpath: //div[@class='raDiv']

		// Step 06 - Wait cho selected date = 09 được visible ((sử dụng: visibility) Xpath: //*[contains(@class,'rcSelected')]//a[text()='09']
		WebElement dateSelectAfter = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		String textAfter = dateSelectAfter.getText().trim();
		System.out.println("textAfter: " + textAfter);

		// Step 07 - Verify ngày đã chọn bằng = Sunday, September 09, 2018
		Assert.assertEquals(textAfter, "Sunday, September 09, 2018");
		
	}
	
	/*Giả lập TC Fail:  
	 * 1. Có dùng ExplicitlyWait
	 * 2. Có wait Ajax Loading xong (DOM bị render lại), nhưng sử dụng lại element mà nhận dạng lại xpath --> lỗi gì*/
	@Test
	public void TC_02_Case03_ExplicitlyWait() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
		WebElement dateTimePicker = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']"));
		Assert.assertTrue(dateTimePicker.isDisplayed());
		
		// Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
		WebElement dateSelectedBefore = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		String textBefore = dateSelectedBefore.getText();
		System.out.println("textBefore: " + textBefore);
		Assert.assertEquals(textBefore, "No Selected Dates to display.");
		
		// Step 04 - Chọn ngày hiện tại (VD: 09/09/2018) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
		WebElement today = driver.findElement(By.xpath("//a[text()='9']"));
		today.click();

		// Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility) Xpath: //div[@class='raDiv']
		By byAjaxLoadingIcon = By.xpath("//div[@class='raDiv']");
		waitExpicit.until(ExpectedConditions.invisibilityOfElementLocated(byAjaxLoadingIcon));
		
		// Giả định sử dụng lại element mà ko nhận dạng lại xpath tại Step 06 - Wait cho selected date = 09 được visible ((sử dụng: visibility) Xpath: //*[contains(@class,'rcSelected')]//a[text()='09']
		String textAfter = dateSelectedBefore.getText().trim();
		System.out.println("textAfter: " + dateSelectedBefore);

		// Step 07 - Verify ngày đã chọn bằng = Sunday, September 09, 2018
		Assert.assertEquals(textAfter, "Sunday, September 09, 2018");
		
	}
	
	@Test
	public void TC_03_FluentWait() throws Exception {
		
		// Step 01 - Truy cập vào trang: https://daominhdam.github.io/fluent-wait/
		driver.get("https://daominhdam.github.io/fluent-wait/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 02 - Wait cho đến khi countdown time được visible (visibility)
		WebElement countDownTime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitExpicit.until(ExpectedConditions.visibilityOf(countDownTime));
		
		// Step 03 - Sử dụng Fluent wait để: Mỗi 1s kiểm tra countdount= 00 được xuất hiện trên page hay chưa (giây đếm ngược về 00)		
		// Tức là trong vòng 15s (tổng thời gian), cứ mỗi 1 giây verify xem nó đã đếm ngược về giây 00 hay chưa
		
		// Khởi tạo Fluent Wait
		new FluentWait<WebElement>(countDownTime)
			// Tổng time là 15s
			.withTimeout(15, TimeUnit.SECONDS)
			// Tần số mỗi 1s sẽ check 1 lần
			.pollingEvery(1, TimeUnit.SECONDS)
			// Nếu gặp exception là find ko thấy element sẽ bỏ qua
			.ignoring(NoSuchElementException.class)
			// Kiểm tra điều kiện
			.until(new Function<WebElement, Boolean>() {
				public Boolean apply(WebElement element) {
	                // Kiểm tra điều kiện countdount = 00
	                boolean flag =  element.getText().endsWith("00");
	                System.out.println("Time = " +  element.getText());
	                // return giá trị cho function apply
	                return flag;
	           }
			});
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
