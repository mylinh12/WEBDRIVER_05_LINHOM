package java_01_basic;

public class Java_01_DataTypes {

	public static void main(String[] args) {
		
		// STEP 01 - Declaring Variables (Khai bao bien)
		// STEP 02 - Naming Variables (Dat ten)
		// STEP 03 - Initialization of Variables (Gan gia tri cho bien)

		/* ============== Boolean ============== */
		System.out.println("======= Boolean ======= ");
		boolean testResult;
		testResult = true;
		System.out.println("Ket qua dung = " + testResult);
		testResult = false;
		System.out.println("Ket qua sai = " + testResult);
		System.out.println("======================");
		
		/* ============== Int ============== */
		System.out.println("======= Int ======= ");
		int cart;
		cart = 2;
		System.out.println("So luong gio hang = " + cart);
		cart = cart + 10 ;
		System.out.println("So luong gio hang = " + cart);
		System.out.println("======================");
		
		/* ============== Double ============== */
		System.out.println("======= Double ======= ");
		double ketqua;
		ketqua = 8.60;
		System.out.println("Ket qua = " + ketqua);
		System.out.println("======================");
		
		/* ============== String ============== */
		System.out.println("======= String ======= ");
		String name, address, city;
		name = "My Linh";
		address = "123 Address";
		city = "Ho Chi Minh";
		System.out.println(name);
		System.out.println(address);
		System.out.println(city);
		System.out.println("======================");
		
	}

}
