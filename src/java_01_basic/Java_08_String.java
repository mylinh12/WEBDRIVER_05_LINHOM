package java_01_basic;

public class Java_08_String {

	public static void main(String args[]) {
		
		/* ============== Length (Do dai) ============== */
		System.out.println("======= length (Do dai) ======= ");
		String automation = "Automation Testing";
		
		int length = automation.length();
		System.out.println(automation);
		System.out.println("Do dai cua chuoi = " + length);		
		System.out.println("======================");
		
		/* ============== charArt (Ki tu tu vi tri) ============== */
		System.out.println("======= charArt (Ki tu tu vi tri) ======= ");
		String chuoi = "Automation Testing Tutorials";
		// |A|u|t|o|m|a|t|i|o|n|
		
		System.out.println(chuoi);
		char kiTu = chuoi.charAt(0);
		System.out.println("Ki tu = " + kiTu);	 // A	
		System.out.println("======================");
		
		/* ============== concate (Noi chuoi) ============== */
		System.out.println("======= concate (Noi chuoi) ======= ");
		System.out.println(automation);
		String testing = automation.concat(" Tutorialss");
		System.out.println("Noi chuoi = " + testing);	
		System.out.println("======================");
		
		/* ============== equal (so sanh tuyet doi) ============== */
		System.out.println("======= equal (so sanh tuyet doi) ======= ");
		System.out.println(testing);
		System.out.println(automation);
		boolean compareValue = testing.equals(automation);
		System.out.println("So sanh chuoi tuyet doi = " + compareValue);
		
		compareValue = automation.equals(automation);
		System.out.println("So sanh chuoi tuyet doi = " + compareValue);
		System.out.println("======================");
		
		/* ============== contains (so sanh tuong doi) ============== */
		System.out.println("======= contains (so sanh tuong doi) ======= ");
		System.out.println(testing);
		System.out.println(automation);
		compareValue = testing.contains(automation);
		System.out.println("So sanh chuoi tuong doi = " + compareValue);
		System.out.println("======================");
		
		/* ============== index (vi tri cua MOT TU trong 1 chuoi) ============== */
		System.out.println("======= index (vi tri cua word trong 1 chuoi) ======= ");
		System.out.println(testing);
		int index = testing.indexOf("Testing");
		System.out.println("Vi tri chuoi = " + index);  // 11
		System.out.println("======================");
		
		/* ============== substring (chuoi con tu vi tri) ============== */
		System.out.println("======= substring (chuoi con tu vi tri) ======= ");
		String testString = "Automation Testing Online Advanced 05";
		int index_01 = testString.indexOf("Online");
		
		System.out.println(testString);
		String subStringStart = testString.substring(index_01);
		System.out.println("Chuoi con tu vi tri index den cuoi cung = " + subStringStart);
		
		String subStringStartToEnd = testString.substring(11, 18);
		System.out.println("Chuoi con tu vi tri index 11 den 18 = " + subStringStartToEnd);
		
		subStringStartToEnd = testString.substring(19, 25);
		System.out.println("Chuoi con tu vi tri index 19 den 25 = " + subStringStartToEnd);
		System.out.println("======================");
		
		/* ============== Replace (Thay the 1 ki tu/ chuoi ki tu/ chuoi khac) ============== */
		System.out.println("======= Replace (Thay the 1 ki tu/ chuoi ki tu/ chuoi khac) ======= ");
		String money = "$100.00";
		System.out.println("money = " + money);
		money = money.replace("$", "");
		System.out.println("Replace $ : " + money);
		double money_ = Double.parseDouble(money);
		System.out.println("Value Double = " + money_);
		
		String url = "http://live.guru99.com/index.php/mobile.html";
		System.out.println("url = " + url);
		url = url.replace("http:", "");
		url = url.replace("/", "");
		System.out.println("new url = " + url);
		System.out.println("======================");
		
		/* ============== Split/ extract number from String (Tach chuoi) ============== */
		System.out.println("======= Split/ extract number from String (Tach chuoi) ======= ");
		String text = "     Viewing 72 of 1879 results    ";
		System.out.println("Text = " + text);
		
		text = text.trim();
		String [] subString = text.split(" ");
		for (int i = 0; i<subString.length; i++) {
			System.out.println("Vi tri thu [" + i + "]: " + subString[i] );
		}
		
		System.out.println(extractNumberFromString(text, 1));
		System.out.println(extractNumberFromString(text, 3));
		System.out.println("======================");
		
		/* ============== UPPER/ lower (HOA/ thuong) ============== */
		System.out.println("======= UPPER/ lower (HOA/ thuong) ======= ");
		testString = "Automation Testing Online Advanced 05";
		System.out.println(testString);
		
		String upper = testString.toUpperCase();
		System.out.println("In HOA = " + upper);
		
		String lower = testString.toLowerCase();
		System.out.println("In thuong = " + lower);
		System.out.println("======================");
		
		/* ============== Trim ( Cat bo khoang trang/ tab/ xuong dong dau va cuoi chuoi) ============== */
		System.out.println("======= Trim ( Cat bo khoang trang/ tab/ xuong dong dau va cuoi chuoi) ======= ");
		String trimString = "    "
				+ "           "
				+ "  Viewing 72 of 1879 results   "
				+ "   "
				+ ""
				+ "        ";
		System.out.println("trim String = " + trimString);
		
		trimString = trimString.trim();
		System.out.println("new trim String = " + trimString);
		System.out.println("======================");

		/* ============== Convert int/ string or double/ string or versa ============== */
		System.out.println("======= Convert int/ string or double/ string or versa ======= ");
		int number = 70;
		
		String stringValue = String.valueOf(number);
		System.out.println("String value 01 = " + stringValue);
		
		stringValue = number + "";
		System.out.println("String value 02 = " + stringValue);
		
		String stringPrice = "1000";
		System.out.println("string Price = " + stringPrice);
		
		int intPrice = Integer.parseInt(stringPrice);
		System.out.println("Value Integer = " + intPrice);
		
		float floatPrice = Float.parseFloat(stringPrice);
		System.out.println("Value Float = " + floatPrice);
		
		double doublePrice = Double.parseDouble(stringPrice);
		System.out.println("Value Double = " + doublePrice);
		System.out.println("======================");
		
		
		
		
		
	}
	
	public static int extractNumberFromString(String text, int index)
	{
		String [] subString = text.split(" ");
		int result = Integer.parseInt(subString[index]);
		return result;
	}
}
