package factory_pattern;

public class CarFactory {
	public void viewCar(String carType) {
		Car car;
		if(carType.equalsIgnoreCase("HONDA")) {
			car = new Honda();
			car.view();
		} else if (carType.equalsIgnoreCase("LEXUS")) {
			car = new Lexus();
			car.view();
		} else if (carType.equalsIgnoreCase("TOYOTA")) {
			car = new Toyota();
			car.view();
		}
	}
}
