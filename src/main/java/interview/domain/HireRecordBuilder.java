package interview.domain;

import java.util.Date;

public class HireRecordBuilder {
    private String vehicleReg;
    private String clientLicenceNumber;
    private Date startDate;
    private Date endDate;
    private double rate;
    private int state;

    public static HireRecordBuilder from(HireRecord record) {
        return new HireRecordBuilder()
                .setVehicleReg(record.getVehicleReg())
                .setClientLicenceNumber(record.getClientLicenceNumber()) //changed to clientLicenceNumber from name
                .setStartDate(record.getStartDate())
                .setEndDate(record.getEndDate())
                .setRate(record.getRate())
                .setState(record.getState());
    }

    public HireRecordBuilder setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
        return this;
    }

    public HireRecordBuilder setClientLicenceNumber(String clientLicenceNumber) {
        this.clientLicenceNumber = clientLicenceNumber;
        return this;
    }

    public HireRecordBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public HireRecordBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public HireRecordBuilder setRate(double rate) {
        this.rate = rate;
        return this;
    }

    public HireRecordBuilder setState(int state) {
        this.state = state;
        return this;
    }

    public HireRecord createHireRecord() {
        return new HireRecord(vehicleReg, clientLicenceNumber, startDate, endDate, rate, state);
    }
}