package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_UserInteractions {
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

	@Test
	public void TC_01_HoverMouse_Case01() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
		driver.get("http://daominhdam.890m.com/");
		
		// Step 02 - Hover chuột vào title: 'Hover over me'
		WebElement hoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		Actions action = new Actions(driver);
		action.moveToElement(hoverText).perform();
		Thread.sleep(3000);
		
		// Step 03 - Verify tooltip này được hiển thị
		////div[@role='tooltip']//div[@class='tooltip-inner']
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='tooltip']//div[@class='tooltip-inner']")).isDisplayed());
		Thread.sleep(3000);
		
		// Step 04 - Get text của tooltip và so sánh nó có phải là 'Hooray!' hay không
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='tooltip']//div[@class='tooltip-inner']")).getText(), "Hooray!");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_01_HoverMouse_Case02() {
		
	}
	
	@Test
	public void TC_02_ClickAndHold_Case01() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/selectable/display-grid.html
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> selectTable = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		Thread.sleep(3000);
		
		// Step 02 - Click and hold từ 1-> 4
		Actions action = new Actions(driver);
		action.clickAndHold(selectTable.get(0)).moveToElement(selectTable.get(3)).release().build().perform();
		Thread.sleep(3000);
		
		// Step 03 - Sau khi chọn kiểm tra rằng có đúng 4 phần tử đã được chọn thành công với xpath //li[@class='ui-state-default ui-selectee ui-selected']
		List <WebElement> selectTableSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(selectTableSelected.size(), 4);
		Thread.sleep(3000);
		
	}
	
	@Test
	public void TC_02_ClickAndHold_Case02() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/selectable/display-grid.html
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> selectTable = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		Thread.sleep(3000);

		// Step 02 - Chỉ chọn các số 1, 3, 5, 7
		Actions action = new Actions(driver);
		
		// giả lập nhấn phím Ctrl xuống
		action.keyDown(Keys.CONTROL).build().perform();
		
		// bắt đầu chọn các số 1, 3, 5, 7
		selectTable.get(0).click();
		selectTable.get(2).click();
		selectTable.get(4).click();
		selectTable.get(6).click();
		
		// giả lập nhả phím Ctrl ra
		action.keyUp(Keys.CONTROL).build().perform();
		
		Thread.sleep(3000);

		// Step 03 - Sau khi chọn kiểm tra rằng có đúng 4 phần tử đã được chọn thành công với xpath //li[@class='ui-state-default ui-selectee ui-selected']
		List<WebElement> selectTableSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(selectTableSelected.size(), 4);
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_03_DoubleClick() throws Exception {
		
		//Step 01 - Truy cập vào trang: http://www.seleniumlearn.com/double-click
		driver.get("http://www.seleniumlearn.com/double-click");
		
		// Step 02 - Double click vào element: Double-Click Me!
		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		
		Actions action = new Actions(driver);
		action.doubleClick(doubleClickButton).build().perform();
		Thread.sleep(3000);
		
		// Step 03 - Verify text trong alert được hiển thị: 'The Button was double-clicked.'
		Alert jsAlert = driver.switchTo().alert();
		Assert.assertEquals(jsAlert.getText(), "The Button was double-clicked.");
		Thread.sleep(3000);
		
		// Step 04 - Accept Javascript alert
		jsAlert.accept();
		Thread.sleep(3000);
		
	}
	
	@Test
	public void TC_04_RighClickToElement() throws Exception {
		
		// Step 01 - Truy cập vào trang: http://swisnl.github.io/jQuery-contextMenu/demo.html
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		// Step 02 - Right click vào element: right click me
		WebElement rightClickMeButton = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions (driver);
		action.contextClick(rightClickMeButton).build().perform();
		Thread.sleep(3000);
		
		// Step 03 - Hover chuột vào element: Quit
		WebElement quitBefore = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		action.moveToElement(quitBefore).build().perform();
		Thread.sleep(3000);
		
		// Step 04 - Verify element Quit (visible + hover) với xpath: //li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']
		WebElement quitAfter = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]"));
		Assert.assertTrue(quitAfter.isDisplayed());
		Thread.sleep(3000);
		
		// Step 05 - Click chọn Quit
		action.click(quitBefore).build().perform();
		
		// Step 06 - Accept Javascript alert
		Alert jsAlert = driver.switchTo().alert();
		jsAlert.accept();
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_05_DragAndDrop_Case01() throws Exception {
		driver.get("http://daominhdam.890m.com/");
		
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_05_DragAndDrop_Case02() throws Exception {
		driver.get("http://daominhdam.890m.com/");
		
		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
