package selenium_api;

import org.testng.annotations.Test;

import com.sun.glass.events.KeyEvent;

import org.testng.annotations.BeforeClass;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
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

public class Topic_09_UploadFile {

	WebDriver driver;
	String projectDirectory = System.getProperty("user.dir");
	String fileName_01 = "upload01.png";
	String fileName_02 = "upload02.png";
	String fileName_03 = "upload03.jpg";
	String uploadFilePath_01 = projectDirectory + "\\images\\" + fileName_01;
	String uploadFilePath_02 = projectDirectory + "\\images\\" + fileName_02;
	String uploadFilePath_03 = projectDirectory + "\\images\\" + fileName_03;

	// AutoIT
	String chromeUpload = projectDirectory + "\\upload\\chrome.exe";
	String firefoxUpload = projectDirectory + "\\upload\\firefox.exe";
	String ieUpload = projectDirectory + "\\upload\\ie.exe";

	// Churcker
	String folderName = "online05" + randomNumber();
	String emailAddress = "mylinh" + randomNumber() + "@mailinator.com";
	String firstName = "My Linh";

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
	public void TC_01_Case01_UploadSingleFile_BySendKey() throws Exception {

		// Step 01 - Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Step 02 - Sử dụng phương thức sendKeys để upload file chạy cho 3 trình duyệt (IE/ Firefox/ Chrome)
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		uploadElement.sendKeys(uploadFilePath_01);
		Thread.sleep(3000);

		// Kiểm tra file đã được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_01 + "']")).isDisplayed());

		// Step 03 - Kiểm tra file đã được tải lên thành công
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_01 + "']")).isDisplayed());

		Thread.sleep(3000);
	}

	@Test
	public void TC_01_Case02_UploadMultipleFiles_BySendKey() throws Exception {

		// Step 01 - Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Step 02 - Sử dụng phương thức sendKeys để upload file chạy cho 3 trình duyệt (IE/ Firefox/ Chrome)
		String filePathList[] = { uploadFilePath_01, uploadFilePath_02, uploadFilePath_03 };

		for (int i = 0; i < filePathList.length; i++) {
			WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
			uploadElement.sendKeys(filePathList[i]);
			Thread.sleep(3000);
		}

		// Kiểm tra file đã được chọn thành công
		String fileNameList[] = { fileName_01, fileName_02, fileName_03 };
		for (int j = 0; j < fileNameList.length; j++) {
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileNameList[j] + "']")).isDisplayed());
			Thread.sleep(3000);
		}

		// Step 03 - Kiểm tra file đã được tải lên thành công
		// Bấm nút 'Start' để bắt đầu upload
		for (int x = 1; x < filePathList.length + 1; x++) {
			driver.findElement(By.xpath("//table//tr[" + x + "]//button[@class='btn btn-primary start']")).click();
			Thread.sleep(3000);
		}

		// Kiểm tra file đã được tải lên thành công
		for (int y = 0; y < fileNameList.length; y++) {
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileNameList[y] + "']")).isDisplayed());
			Thread.sleep(3000);
		}

		Thread.sleep(3000);
	}

	@Test
	public void TC_02_Case01_UploadFileByAutoIT_FirefoxBrowser() throws Exception {

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Step 01 - Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Step 02 - Sử dụng AutoIT để upload file chạy cho trình duyệt Firefox
		WebElement uploadElement = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadElement.click();

		// Giả lập upload file bằng AutoIT : firefox browser (Pass)
		Runtime.getRuntime().exec(new String[] { firefoxUpload, uploadFilePath_01 });
		Thread.sleep(3000);

		// Kiểm tra file đã được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_01 + "']")).isDisplayed());

		// Step 03 - Kiểm tra file đã được tải lên thành công
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_01 + "']")).isDisplayed());

		Thread.sleep(3000);

	}

	@Test
	public void TC_02_Case02_UploadFileByAutoIT_ChromeBrowser() throws Exception {

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Step 01 - Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Step 02 - Sử dụng AutoIT để upload file chạy cho 3 trình duyệt Chrome
		WebElement uploadElement = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadElement.click();

		// Giả lập upload file bằng AutoIT : chrome browser
		Runtime.getRuntime().exec(new String[] { chromeUpload, uploadFilePath_01 });
		Thread.sleep(3000);

		// Kiểm tra file đã được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_01 + "']")).isDisplayed());

		// Step 03 - Kiểm tra file đã được tải lên thành công
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_01 + "']")).isDisplayed());

		Thread.sleep(3000);

	}

	@Test
	public void TC_02_Case03_UploadFileByAutoIT_IEBrowser() throws Exception {

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Step 01 - Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Step 02 - Sử dụng AutoIT để upload file chạy cho 3 trình duyệt IE
		// IE browser: (1): phải setting cho IE về default setting; (2) Zoom = 100%; (3) Security (4 zones) = enable
		WebElement uploadElement = driver.findElement(By.xpath("//span[contains(text(),'Add files...')]"));
		uploadElement.click();

		// Giả lập upload file bằng AutoIT : IE browser (Pass)
		Runtime.getRuntime().exec(new String[] { ieUpload, uploadFilePath_01 });
		Thread.sleep(3000);

		// Kiểm tra file đã được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_01 + "']")).isDisplayed());

		// Step 03 - Kiểm tra file đã được tải lên thành công
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_01 + "']")).isDisplayed());

		Thread.sleep(3000);

	}

	@Test
	public void TC_03_Robot() throws Exception {

		// Step 01 - Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Step 02 - Sử dụng Robot để upload file chạy cho 3 trình duyệt (IE/ Firefox/ Chrome) (all browser are passed)
		// Define 'locator' của fileName
		StringSelection select = new StringSelection(uploadFilePath_01);

		// Copy 'locator' vào clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		// Click 'Add file' button
		WebElement uploadElement = driver.findElement(By.cssSelector(".fileinput-button"));
		uploadElement.click();
		Thread.sleep(3000);

		// Tạo một Robot giả lập user.
		Robot robot = new Robot();

		// Focus to textbox
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

		// Giả lập nhấn xuống phím Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);

		// Giả lập nhả phím Ctrl - V ra
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);

		// Tương đương click nút 'Open' btn trong cửa sổ 'Open File' dialog
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

		// Kiểm tra file đã được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName_01 + "']")).isDisplayed());

		// Step 03 - Kiểm tra file đã được tải lên thành công
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + fileName_01 + "']")).isDisplayed());

		Thread.sleep(3000);

	}

	@Test
	public void TC_04_UploadFileChucker() throws Exception {

		// Step 01 - Open URL: 'https://encodable.com/uploaddemo/'
		driver.get("https://encodable.com/uploaddemo/");

		// Step 02 - Choose Files to Upload (Ex: UploadFile.jpg)
		// input[@type='file']
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(uploadFilePath_01);

		// Step 03 - Select dropdown (Upload to: /uploaddemo/files/)
		// select[@name='subdir1']
		WebElement uploadDropdown = driver.findElement(By.xpath("//select[@name='subdir1']"));
		Select selectDropDown = new Select(uploadDropdown);
		selectDropDown.selectByVisibleText("/uploaddemo/files/");

		// Step 04 - Input random folder to 'New subfolder? Name:) textbox (Ex: dam1254353)
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(folderName);

		// Step 05 - Input email and firstname (dam@gmail.com/ DAM DAO)
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(firstName);

		// Step 06 - Click Begin Upload (Note: Wait for page load successfully)
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		Thread.sleep(10000);

		// Step 07 - Verify information; + Email Address: dam@gmail.com/ First Name: DAM DAO; + File name: UploadFile.jpg
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='Email Address: " + emailAddress + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[text()='First Name: " + firstName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dl[@id='fcuploadsummary']//dt[contains(text(), 'File 1 of 1:')]/a[text()='" + fileName_01 + "']")).isDisplayed());

		// Step 08 - Click 'View Uploaded Files' link
		driver.findElement(By.xpath("//div[@id='fcfooter-text']/a[text()='View Uploaded Files']")).click();

		// Step 09 - Click to random folder (Ex: dam1254353)
		WebElement folderNameElement = driver.findElement(By.xpath("//a[text()='" + folderName + "']"));
		highlightElement(folderNameElement);
		Thread.sleep(3000);
		folderNameElement.click();
		Thread.sleep(3000);

		// Step 09 - Verify file name exist in folder (UploadFile.jpg)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName_01 + "']")).isDisplayed());
		Thread.sleep(3000);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	/* Random number */
	public int randomNumber() {
		Random rd = new Random();
		return rd.nextInt(999999);
	}

	/* Highlight an element: arguments[0].style.border='6px groove red' */
	public void highlightElement(WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove yellow'", element);
		Thread.sleep(2000);
	}
}
