package interview.service;

import interview.db.DbServicesDao;
import interview.domain.*;
import interview.offers.DiscountService;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

/*
 * Main hire service class
 */
public class HireService {
	DbServicesDao dbServicesDao;
	//called by rest servlet to make booking
	public String hire(String name,
					   String licenseNumber,
					   String cd,
					   String reg,
					   String start,
					   int days,
					   double rate) throws SQLException, VehicleHiredAlreadyException, VehicleMissingException {
		DbServicesDao dbServicesDao = new DbServicesDao(cd);
		
		Optional<Vehicle> vehicle = dbServicesDao.getVehicleDetails(reg);
		if (!vehicle.isPresent()) {
//			log.error("vehicle doesn't exist"); ToDO
			throw new VehicleMissingException(reg);
		}

		dbServicesDao.createClientIfMissing(name, licenseNumber);

		Date startDate = Date.valueOf(start);
		double discountedRate = DiscountService.INSTANCE.getDiscountedRateFor(vehicle.get(), rate);
		HireRecord hireRecord =
				new HireRecordBuilder()
						.setVehicleReg(reg)
						.setClientLicenceNumber(licenseNumber) //changed to clientLicenceNumber from name
						.setStartDate(startDate)
						.setEndDate(LocalDate.fromDateFields(startDate).plusDays(days-1).toDate())
						.setRate(discountedRate)
						.setState(1) //??
						.createHireRecord();
		dbServicesDao.createNewHireInDb(hireRecord);

		//Removed the following as the vehicle state should never change now
		//db.saveToDatabase(vehicle);

		return hireRecord.getHireno();
	}

	public void markReturned(String cd, long hireno) throws SQLException {
//		try { - there is no point catching exception
			Optional<HireRecord> existingHireRecord = dbServicesDao.getHireRecord(hireno);
			if (existingHireRecord.isPresent() && existingHireRecord.get().isHired()) {
				HireRecord hireRecord = HireRecordBuilder.from(existingHireRecord.get()).createHireRecord();
				dbServicesDao.saveToDatabase(hireRecord);
			}
			//Not needed anymore ->
//			Car car = (Car) db.loadFromDb(cd, "select * from crs where hrrnm = " + hireno, Car.class);
//			car.release(db, cd);
//		} catch (SQLException e) {
			//nothing we can do
//		}
	}
}
