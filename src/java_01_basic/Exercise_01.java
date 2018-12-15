package java_01_basic;

public class Exercise_01 {

	public static void main(String[] args) {

		String originalString = "Automation 01 Testing 345 Totorials Online 123456";

		System.out.println(originalString);

		System.out.println("======= 1. Dem so luong ki tu la 'a' co trong chuoi tren (ket qua = 3) ======= ");
		int count = countDisplayingFrequenceOfChar(originalString, "a");
		System.out.println("So luong ki tu 'a' = " + count);
		System.out.println("======================");

		System.out.println("======= 2. Kiem tra chuoi co chua tu 'Testing' hay ko (ket qua = true) ======= ");
		boolean contain = false;
		contain = originalString.contains("Testing");
		System.out.println("contain 'Tesing' = " + contain);
		System.out.println("======================");

		System.out.println("======= 3. Kiem tra chuoi co bat dau bang 'Automation' hay ko (ket qua = true) ======= ");
		boolean startWithAutomation = false;
		startWithAutomation = originalString.startsWith("Automation");
		System.out.println("Start with 'Automation' = " + startWithAutomation);
		System.out.println("======================");

		System.out.println("======= 4. Kiem tra chuoi co ket thuc bang 'Online' hay ko (ket qua = false) ======= ");
		boolean endWithOnline = false;
		endWithOnline = originalString.endsWith("Online");
		System.out.println("End with 'Online' = " + endWithOnline);
		System.out.println("======================");

		System.out.println("======= 5. Lay vi tri cua tu 'Totorials' co trong chuoi (ket qua = 26) ======= ");
		int indexOfTotorials = 0;
		indexOfTotorials = originalString.indexOf("Totorials");
		System.out.println("Inidex of 'Totorials' = " + indexOfTotorials);
		System.out.println("======================");

		System.out.println("======= 6. Thay the tu 'Online' bang 'Offline' (ket qua = Automation 01 Testing 345 Totorials Offline 123456) ======= ");
		String newString = "";
		newString = originalString.replace("Online", "Offline");
		System.out.println(originalString);
		System.out.println("replace 'Online' to 'Offline' = " + newString);
		System.out.println("======================");

		System.out.println("======= 7. Dem so luong ki tu la so co trong chuoi (ket qua = 11) ======= ");
		int countNumber = 0;
		System.out.println(originalString);
		
		countNumber = countDisplayingOfNumber(originalString);
		System.out.println("Number of number = " + countNumber);
		System.out.println("======================");

	}

	public static int countDisplayingFrequenceOfChar(String originalString, String aCharacter) {
		int count = 0;
		String parseString = "";
		originalString = originalString.toLowerCase();
		aCharacter = aCharacter.toLowerCase();
		char[] subChar = originalString.toCharArray();
		for (int i = 0; i < subChar.length; i++) {
			parseString = String.valueOf(subChar[i]);
			if (parseString.equals(aCharacter)) {
				count++;
			}
		}
		return count;
	}

	// Cach 1
	public static int countDisplayingOfNumber(String originalString) {
		int numberOfNumber = 0, numberOfLetter = 0, numberOfSymbol = 0;
		char[] subChar = originalString.toCharArray();;
		{
			for (int i = 0; i < subChar.length; i++) {
				if (Character.isLetter(subChar[i])) {
					numberOfLetter++;
				} else if (Character.isDigit(subChar[i])) {
					numberOfNumber++;
				} else {
					numberOfSymbol++;
				}
			}
		}
		return numberOfNumber;
	}
	
	// Cach 2
//	public static int countDisplayingOfNumber(String originalString) {
//	int numberOfNumber = 0, numberOfLetter = 0, numberOfSymbol = 0;
//	char[] subChar = originalString.toCharArray();;
//	{
//		for (int i = 0; i < subChar.length; i++) {
//			if ((subChar[i] >= 'a' && subChar[i] <= 'z') || (subChar[i] >= 'A' && subChar[i] <= 'Z')) {
//				numberOfLetter++;
//			} else if (subChar[i] >= '0' && subChar[i] <= '9') {
//				numberOfNumber++;
//			} else {
//				numberOfSymbol++;
//			}
//		}
//	}
//	return numberOfNumber;
//}

}
