package java_exception_designpatern;

public class JavaException {

	public static void main(String[] args) {

		// demo_01_ShowSomeErrors();
		// demo_02a_koDungTryCatch();
		// demo_02b_tryCatch();
		// demo_02c_multiTryCatchs();
		demo_02d_MainTryCatch();

	}

	public static void demo_01_ShowSomeErrors() {
		String name = "LinhOM";
		System.out.println("Name: " + name);

		int number = Integer.parseInt(name);
		System.out.println("Number: " + number);

		int price[] = new int[5];
		price[10] = 50;
	}

	public static void demo_02a_koDungTryCatch() {

		int data = 50 / 0; // nem ra ngoai le
		System.out.println("Rest step");

	}

	public static void demo_02b_tryCatch() {

		// Step 01
		try {
			int data = 50 / 0; // nem ra ngoai le
		} catch (ArithmeticException e) {
			System.out.println(e);

			// neu co dong throe (e); ben duoi thi se fail tai step nay luon, ko lam tiep step 02, 03 nua.
			// throw (e);
		}

		// Step 02
		System.out.println("Rest step 02");

		// Step 03
		System.out.println("Rest step 03");
	}

	public static void demo_02c_multiTryCatchs() {

		// Step 01
		try {
			int a[] = new int[5];
			a[10] = 30 / 0;
		} catch (ArithmeticException e) {
			System.out.println(e);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		// Step 02
		System.out.println("Rest step 02");
	}

	public static void demo_02d_MainTryCatch() {

		// Step 01
		try {
			int a[] = new int[5];
			a[10] = 30 / 0;
		} catch (Exception e) {
			System.out.println(e);
		}

		// Step 02
		System.out.println("Rest step 02");
	}

}
