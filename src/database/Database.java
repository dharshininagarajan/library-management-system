package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/library";
    private static final String userName = "root";
    private static final String password = "Dharshini123@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}
