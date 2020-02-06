package interview.domain;

public abstract class Vehicle {
	private final String make;
	private final String reg;
	private final int category;

	public Vehicle(String reg) {
		this(reg, null, 0);
	}

	public Vehicle(String reg, String make, int category) {
		this.make = make;
		this.reg = reg;
		this.category = category;
	}

	public int getAge() {
		return 2015 - Integer.parseInt("20" +reg.substring(3, 2));
	}

	//The following doesn't belong in the domain model
	//Also it is not needed anymore as the vehcle state doesn't change anymore
//    public void hire(Vehicle vehicle, DbService dbService, HireRecord record) throws SQLException {
//        vehicle.hireEnd = new DateTime(record.getStartDate().getTime()).plusDays(record.getDays()).toDate();
//        hireNumber = record.getHireno();
//        dbService.saveToDatabase(vehicle, cd);
//    }
//
//    public void release(long hireno) throws SQLException {
//        Optional<HireRecord> hireRecord = getHireRecord(hireno);
//        if (hireRecord.isPresent()) {
//            hirere
//        }
//        hireNumber = 0;
//        hireEnd = null;
//        dbService.saveToDatabase(this, cd);
//    }
}
