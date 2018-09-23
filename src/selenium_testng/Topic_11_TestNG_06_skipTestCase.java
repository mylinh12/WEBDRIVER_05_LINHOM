package selenium_testng;

import org.testng.annotations.Test;

public class Topic_11_TestNG_06_skipTestCase {
	@Test(priority = 5, enabled = false)
	public void TC_01() {
		System.out.println("Test case 01");
	}

	// @Test (groups = {"customer", "manager"}, priority = 4)
	public void TC_02() {
		System.out.println("Test case 02");
	}

	@Test(groups = "manager", priority = 3)
	public void TC_03() {
		System.out.println("Test case 03");
	}

	@Test(groups = "payment", priority = 2)
	public void TC_04() {
		System.out.println("Test case 04");
	}

	@Test(groups = "customer", priority = 1)
	public void TC_05() {
		System.out.println("Test case 05");
	}
}
