package interview.db;

import java.sql.SQLException;

public interface DbService {
    Object loadFromDb(String connectionDetails, String sql, Class type) throws SQLException;

    Object saveToDatabase(Object data, String connectionDetails) throws SQLException;

    void delete(String id, Class type, String connectionDetails) throws SQLException;
}