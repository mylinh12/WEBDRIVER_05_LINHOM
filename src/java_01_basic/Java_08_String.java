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
		System.out.println("Ki tu = " + kiTu);		
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
		
		/* ============== index (vi tri cua word trong 1 chuoi) ============== */
		System.out.println("======= index (vi tri cua word trong 1 chuoi) ======= ");
		System.out.println(testing);
		int index = testing.indexOf("Testing");
		System.out.println("Vi tri chuoi = " + index);
		System.out.println("======================");
		
		/* ============== substring (chuoi con tu vi tri) ============== */
		System.out.println("======= substring (chuoi con tu vi tri) ======= ");
		System.out.println(testing);
		String subStringStart = testing.substring(index);
		System.out.println("Chuoi con = " + subStringStart);
		
		String subStringStartToEnd = testing.substring(11, 18);
		System.out.println("Chuoi con = " + subStringStartToEnd);
		System.out.println("======================");
		
		
		
		
		
		
		
		
		
	}
}
