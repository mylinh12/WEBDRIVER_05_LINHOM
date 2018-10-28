package java_01_basic;

public class Java_03_Operator {

	public static void main(String[] args) {

		/* ============== Assign ============== */
		System.out.println("======= Assign: '='  ======= ");
		int time = 10;
		String name = "My Linh";
		boolean value = false;
		System.out.println("Time = " + time);
		System.out.println("Name = " + name);
		System.out.println("Value = " + value);
		System.out.println("======================");

		/* ============== Math ============== */
		System.out.println("======= Math: '+ - * / % ++ --'  ======= ");
		int a, b = 10, c = 5;
		a = b + c;
		System.out.println("Cong = " + a);

		a = b - c;
		System.out.println("Tru = " + a);

		a = b * c;
		System.out.println("Nhan = " + a);

		a = b / c;
		System.out.println("Chia lay nguyen = " + a);
		// ket qua = 2

		a = b % c;
		System.out.println("Chia lay du = " + a);
		// ket qua = 0

		b++;
		System.out.println("Tang len 1 = " + b);
		// ket qua = 11

		c--;
		System.out.println("Giam xuong 1 = " + c);
		// ket qua = 4
		System.out.println("======================");

		/* ============== Relation (true / false) -> boolean ============== */
		System.out.println("======= Relation (true / false) -> boolean ======= ");
		int ten = 10;
		int twenty = 20;
		int thirty = 30;

		System.out.println("Lon hon = " + (ten > twenty));
		// false

		System.out.println("Lon hon or bang = " + (thirty >= twenty));
		// true

		System.out.println("Nho hon = " + (thirty < twenty));
		// false

		System.out.println("Nho hon or bang = " + (thirty <= twenty));
		// false

		System.out.println("Bang bang = " + (thirty == twenty + ten));
		// true

		System.out.println("Khac bang = " + (thirty != twenty + ten));
		// false
		System.out.println("======================");

		/* ============== Logic ============== */
		System.out.println("======= Logic AND (&&) - OR (||) ======= ");
		boolean value_01 = true;
		boolean value_02 = false;

		System.out.println("Ca 2 deu dung = " + (value_01 && value_02));
		// false

		System.out.println("Mot trong 2 dung = " + (value_01 || value_02));
		// true

		System.out.println("Phu dinh = " + (!value_01));
		// false
		System.out.println("======================");

		/* ============== Condition ============== */
		System.out.println("======= Condition: Variable = (expression) ? value_true : value_false ======= ");
		int first = 10;
		int second = 20;
		int third = 30;
		boolean bValue;
		int iValue;

		// dich: 'third' co BANG 'first + second' ko ? Neu BANG thi gan 'true' cho bValue, neu KO BANG thi gan 'false' cho bValue
		bValue = (third == first + second) ? true : false;
		System.out.println("Gia tri = " + bValue);
		// true

		// dich: 'third' co BANG 'second + first' ko ? Neu BANG thi gan '50' cho iValue, neu KO BANG thi gan '100' cho iValue
		iValue = (third == second + first) ? 50 : 100;
		System.out.println("Ket qua = " + iValue);
		// 50

		iValue = (!(third == second + first)) ? 50 : 100;
		System.out.println("Ket qua = " + iValue);
		// 100
		System.out.println("======================");
	}

}
