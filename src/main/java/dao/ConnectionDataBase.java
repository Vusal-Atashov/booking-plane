package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDataBase {
    public Connection getConnection() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres")) {
            return conn;
        } catch (Exception e) {
            throw new RuntimeException("Can't connect to the database", e);
        }
    }
}
