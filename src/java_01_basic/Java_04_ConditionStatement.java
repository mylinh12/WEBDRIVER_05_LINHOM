package java_01_basic;

public class Java_04_ConditionStatement {

	public static void main(String[] args) {
		
		/* ============== If ============== */
		System.out.println("======= If ======= ");
		boolean value = true;
		if(value == true) {
			System.out.println("Value = " + value);
		}
		System.out.println("======================");
		
		int diem = 10;
		if(diem == 10) {
			System.out.println("Hoc sinh xuat sac");
		}
		System.out.println("======================");
		
		/* ============== If - else if - else ============== */
		System.out.println("======= If - else - if - else ======= ");
		String browser = "ie";
		if(browser == "chrome") {
			System.out.println("Khoi tao trinh duyet CHROME");
		} else if (browser == "firefox") {
			System.out.println("Khoi tao trinh duyet FIREFOX");
		} else {
			System.out.println("Khoi tao trinh duyet IE");
		}
		System.out.println("======================");
		
		/* ============== switch - case ============== */
		// performace cua switch case nhanh hon If-Else
		System.out.println("======= VD1: switch - case ======= ");
		String browser_ = "safari";
		switch(browser_) {
		case "safari":
		case "chrome":
			System.out.println("Khoi tao trinh duyet CHROME");
			break;
		case "firefox":
			System.out.println("Khoi tao trinh duyet FIREFOX");
			break;
		case "ie":
			System.out.println("Khoi tao trinh duyet IE");
			break;
		default:
			System.out.println("Khoi tao trinh duyet SAFARI");
			break;
		}
		System.out.println("======================");
		
		
		System.out.println("======= VD2: switch - case ======= ");
		String page = "Home Page";
		switch(page) {
		case "New Customer":
			System.out.println("Open New Customer page");
			break;
		case "Edit Customer":
			System.out.println("Open Edit Customer page");
			break;
		case "Delete Customer":
			System.out.println("Open Delete Customer page");
			break;
		default:
			System.out.println("Open Home page");
			break;
		}
		System.out.println("======================");
		
	}

}
