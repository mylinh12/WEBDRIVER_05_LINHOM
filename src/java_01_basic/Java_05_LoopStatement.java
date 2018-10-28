package java_01_basic;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Java_05_LoopStatement {

	public static void main(String[] args) {

		WebDriver driver;
		List<WebElement> elements;
		int i = 0;
		int y = 0;

		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		elements = driver.findElements(By.xpath("//a"));

		int number = elements.size();
		System.out.println("All links = " + number);

		/* ============== For ============== */
		System.out.println("======= For ======= ");
		for (i = 0; i < elements.size(); i++) {
			System.out.println("Link in for loop [" + i + "] = " + elements.get(i).getAttribute("href"));
		}
		System.out.println("======================");

		/* ============== For - each (Only for Array /List) ============== */
		System.out.println("======= For - each (Only for Array /List) ======= ");
		int loop = 0;
		for (WebElement element : elements) {
			System.out.println("Link in for-each [" + loop + "] = " + element.getAttribute("href"));
			loop++;
		}
		System.out.println("======================");

		/* ============== While ============== */
		System.out.println("======= While: kiem tra dieu kien truoc, neu DUNG thi moi action ======= ");
		i = 0; y = 0;
		while (i < number) {
			System.out.println("Link in WHILE [" + i + "] = " + elements.get(i).getAttribute("href"));
			i++;
		}

		/* ============== Do - while ============== */
		System.out.println("======= Do - while: action truoc roi moi kiem tra dieu kien; luon duoc thuc hien it nhat 1 lan ======= ");
		do {
			System.out.println("Link in WHILE [" + y + "] = " + elements.get(y).getAttribute("href"));
			y++;
		} while (y < number);

		driver.quit();
	}

}
