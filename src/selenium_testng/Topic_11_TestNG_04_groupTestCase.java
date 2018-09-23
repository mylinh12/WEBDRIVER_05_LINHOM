package selenium_testng;

import org.testng.annotations.Test;

public class Topic_11_TestNG_04_groupTestCase {
	
  // Single group
  @Test (groups = "customer")
  public void TC_01() {
	  System.out.println("Test case 01");
  }
  
  // Multi groups
  @Test (groups = {"customer", "manager"})
  public void TC_02() {
	  System.out.println("Test case 02");
  }
  
  @Test (groups = "manager")
  public void TC_03() {
	  System.out.println("Test case 03");
  }
  
  @Test (groups = "payment")
  public void TC_04() {
	  System.out.println("Test case 04");
  }
  
  @Test (groups = "customer")
  public void TC_05() {
	  System.out.println("Test case 05");
  }

}
