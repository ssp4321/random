package interview.domain;

import java.util.Date;

public class Car extends Vehicle {
    public Car(String reg) {
        super(reg);
    }

    public Car(String reg, String make, int category) {
        super(reg, make, category);
    }

    public Car(String make, String reg, int category, boolean hired, Date hireEnd) {
        super(make, reg, category);
    }
}
