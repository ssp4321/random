package interview.domain;

import org.joda.time.LocalDateTime;

import java.util.Date;
import java.util.UUID;

public class HireRecord {
	private final String vehicleReg; //Changed from Vehicle to vehicleReg
	private final String clientLicenceNumber; //Changed from 'client'
	private final Date startDate;
	private final Date endDate; //adding this as it would then be easy to lookup in DB
	//private final int days; //removing this to avoid duplication of info
	private final double rate;
	private final int state;
	private final String hireno;


	public HireRecord(String vehicleReg,
                      String clientLicenceNumber,
                      Date startDate,
                      //int days,
                      Date endDate, //added this so that the hire can be done hourly too and can be ended before midnight
                      double rate,
                      int state) {
//		if (days <= 0) {
//			throw new IllegalArgumentException("days has to be greater than 0");
//		}
		this.vehicleReg = vehicleReg;
		this.clientLicenceNumber = clientLicenceNumber;
		this.startDate = startDate;
				//LocalDate.fromDateFields(startDate).toDate();
		this.rate = rate;
		this.state = state;
		this.hireno = UUID.randomUUID().toString(); //hireNo is auto-generated
		this.endDate = endDate;
				//LocalDateTime.fromDateFields(startDate).plusDays(days-1).toDate();
	}

	public String getVehicleReg() {
		return vehicleReg;
	}

	public String getClientLicenceNumber() {
		return clientLicenceNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {return endDate;}

	public int getState() {
		return state;
	}

	public String getHireno() {
		return hireno;
	}

	public double getRate() {
	//		Moved the discount-logic to another service - as discounts are likely to be temporary
	//		if(car instanceof Bike) return rate;  //no discount for bikes
	//		return this.rate = car.getAge() > 3 ? rate * 0.9 : rate;  //discount for older cars
		return rate;
	}

	public boolean isHired() {
		//Account for 'time' in endDate here to account for ending the hire before midnight
		return !LocalDateTime.fromDateFields(endDate).isBefore(LocalDateTime.now());
	}

	//	Not needed anymore
	//	public int getDays() {
	//		return Days.daysBetween(
	//				LocalDate.fromDateFields(startDate),
	//				LocalDate.fromDateFields(endDate));
	//	}

}
