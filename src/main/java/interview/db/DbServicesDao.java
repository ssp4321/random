package interview.db;

import interview.domain.Client;
import interview.domain.HireRecord;
import interview.domain.Vehicle;
import interview.domain.VehicleHiredAlreadyException;
import org.joda.time.LocalDate;

import java.sql.SQLException;
import java.util.Optional;

public class DbServicesDao {
    private final String cd;
    private DbService db;

    public DbServicesDao(String cd) {
        this.cd = cd;
    }

    public Object saveToDatabase(Object data) throws SQLException {
        return db.saveToDatabase(data, cd);
    }

    public Optional<HireRecord> getHireRecord(long hireNo) throws SQLException {
        //hr = hireRecord table
        return Optional.ofNullable((HireRecord) db.loadFromDb(cd, "select * from hr where hireno = " + hireNo, HireRecord.class));
    }

    public Optional<HireRecord> getCurrentHireRecordForVehicle(String vehicleReg) throws SQLException {
        //hr = hireRecord table
        return Optional.ofNullable((HireRecord) db.loadFromDb(
                cd,
                String.format("select * from hr where vehreg = '%' and enddate >= '%'",
                        vehicleReg, LocalDate.now().plusDays(1).toString("ddMMyyyy")),
                HireRecord.class));
    }

    //Changed from getCarDetails to getVehicleDetails
    public Optional<Vehicle> getVehicleDetails(String rg) throws SQLException {
        return Optional.ofNullable((Vehicle) db.loadFromDb(cd, buildGetCarDetailsSql(rg), Vehicle.class));
    }

    public boolean createClientIfMissing(String name, String licenseNumber) throws SQLException {
        int count = (int) db.loadFromDb(cd, buildGetClientCountFromDbSql(licenseNumber), Integer.class);
        if (count == 0) {
            createClientInDb(name, licenseNumber);
        }
        return true;
    }

    private Client createClientInDb(String name, String licenseNumber) throws SQLException {
        Client client = new Client(name, licenseNumber, cd);
        db.saveToDatabase(client, cd);
        return client;
    }

    private String buildGetClientCountFromDbSql(String licenseNumber) {
        //Important - the SQL is prone to sql-attack. So let's make sure the name/id supplied is not more than one word
        verifyOneWord(licenseNumber);

        //changed SQL to use ''licenseNumber' as the predicate instead of name
        return "select count(*) from clients where licenseNumber = " + licenseNumber;
    }

    private String buildGetCarDetailsSql(String rg) {
        //Important - the SQL is prone to sql-attack. So let's make sure the name/id supplied is not more than one word
        if (rg.split(" ").length > 1) {
            throw new IllegalArgumentException("name has to be one word");
        }

        return "select * from clients where clientId = " + rg;
    }

    private void verifyOneWord(String name) {
        if (name.split(" ").length > 1) {
            throw new IllegalArgumentException("name has to be one word");
        }
    }

    public void  createNewHireInDb(HireRecord hireRecord) throws SQLException, VehicleHiredAlreadyException {
        String vehicleReg = hireRecord.getVehicleReg();
        synchronized (vehicleReg.intern()) { //important
            Optional<HireRecord> currentHire = getCurrentHireRecordForVehicle(hireRecord.getVehicleReg());

            if (currentHire.isPresent()) {
//                log.error("vehicle already hired"); ToDo
                throw new VehicleHiredAlreadyException(hireRecord.getVehicleReg());
            }

            saveToDatabase(hireRecord);
        }
    }
}
