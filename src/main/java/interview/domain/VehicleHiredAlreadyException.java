package interview.domain;

public class VehicleHiredAlreadyException extends Exception {
    private String vehicleReg;

    public VehicleHiredAlreadyException(String vehicleReg) {
        this.vehicleReg = vehicleReg;
    }
}
