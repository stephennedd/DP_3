package DP_P3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleBaseDAO {
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String DB_USER = "P2";
    private static final String DB_PASS = "STEPHEN";
    private static Connection conn;

    public OracleBaseDAO() {
        try{
            if (conn == null) {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() { return conn; }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
