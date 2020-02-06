package interview.offers;

import interview.domain.Car;
import interview.domain.Vehicle;

//Created a specialized service as the discount could be temporary and hence don't want the logic to be part of the domain model
public class DiscountService {
    public static DiscountService INSTANCE = new DiscountService();

    private DiscountService() {
    }
    /**
     * Discount for older cars
     * @param car
     * @param fullPrice
     * @return
     */
    public double getDiscountedRateFor(Car car, double fullPrice) {
        return car.getAge() > 3 ? fullPrice * 0.9 : fullPrice;
    }

    public double getDiscountedRateFor(Vehicle nonCar, double fullPrice) {
        return fullPrice;
    }
}
