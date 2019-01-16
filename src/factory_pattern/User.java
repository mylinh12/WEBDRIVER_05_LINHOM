package factory_pattern;

public class User {

	public static void main(String[] args) {
		User user = new User();
		// user.viewHondon();
		// user.viewHondon();
		// user.viewToyota();
		user.viewCar();
	}

	public void viewHondon() {
		Honda honda = new Honda();
		honda.view();
	}

	public void viewLexus() {
		Lexus lexus = new Lexus();
		lexus.view();
	}

	public void viewToyota() {
		Toyota toyota = new Toyota();
		toyota.view();
	}
	
	public void viewCar() {
		CarFactory carFactory = new CarFactory();
		carFactory.viewCar("HONDA");
		carFactory.viewCar("LEXUS");
		carFactory.viewCar("TOYOTA");
	}
}
