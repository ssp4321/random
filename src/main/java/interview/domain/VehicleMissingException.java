package interview.domain;

public class VehicleMissingException extends Exception {
    private String vehicleReg;

    public VehicleMissingException(String vehicleReg) {
        this.vehicleReg = vehicleReg;
    }
}
