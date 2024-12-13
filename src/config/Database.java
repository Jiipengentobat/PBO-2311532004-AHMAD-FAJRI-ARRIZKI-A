package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Database {
    private static Database instance; // Singleton instance
    private Connection connection; // Connection instance

    private static final Logger logger = Logger.getLogger(Database.class.getName());

    // Private constructor untuk mencegah instansiasi langsung
    private Database() {
        try {
            String url = "jdbc:mysql://localhost/laundry_apps";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Database connection established successfully.");
        } catch (SQLException e) {
            logger.severe("Error connecting to database: " + e.getMessage());
        }
    }

    // Method untuk mendapatkan instance Singleton
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Method untuk mendapatkan koneksi database
    public Connection getConnection() {
        return connection;
    }
}
