package interview.domain;

/*
 * Andyr request want to hire bikes too 
 */
public class Bike extends Vehicle {
	public Bike(String reg) {
		super(reg);
	}

	public Bike(String make, String reg, int category) {
		super(make, reg, category);
	}
}
