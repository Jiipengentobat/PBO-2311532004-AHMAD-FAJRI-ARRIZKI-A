package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.Database;
import model.Pelanggan;

public class CustomerRepo implements CustomerDAO {
    private static final Logger logger = Logger.getLogger(CustomerRepo.class.getName()); // Logger untuk mencatat error
    private static CustomerRepo instance; // Singleton instance
    private Connection connection;

    private final String insert = "INSERT INTO customer (nama, email, alamat, hp) VALUES (?, ?, ?, ?);";
    private final String select = "SELECT * FROM customer;";
    private final String delete = "DELETE FROM customer WHERE id = ?;";
    private final String update = "UPDATE customer SET nama = ?, email = ?, alamat = ?, hp = ? WHERE id = ?;";

    // Private constructor untuk Singleton
    public CustomerRepo() {
        connection = Database.getInstance().getConnection(); // Mendapatkan koneksi dari Database
    }

    // Static method untuk mendapatkan instance tunggal
    public static synchronized CustomerRepo getInstance() {
        if (instance == null) {
            instance = new CustomerRepo();
        }
        return instance;
    }

    @Override
    public List<Pelanggan> show() {
        List<Pelanggan> ls = new ArrayList<>();
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                // Menggunakan Builder untuk membangun objek Pelanggan
                Pelanggan cs = new Pelanggan.Builder()
                    .setId(rs.getString("id"))
                    .setNama(rs.getString("nama"))
                    .setEmail(rs.getString("email"))
                    .setAlamat(rs.getString("alamat"))
                    .setHp(rs.getString("hp"))
                    .build();
                ls.add(cs);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while retrieving customer data from the database.", e); // Log error lebih jelas
        }
        return ls;
    }

    @Override
    public void save(Pelanggan cs) {
        try (PreparedStatement st = connection.prepareStatement(insert)) {
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getHp());
            st.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while saving customer: " + cs, e); // Log error dengan informasi lebih jelas
        }
    }

    @Override
    public void update(Pelanggan cs) {
        try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getHp());
            st.setString(5, cs.getId()); // Menggunakan ID pelanggan untuk update
            st.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while updating customer: " + cs, e); // Log error lebih jelas
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement st = connection.prepareStatement(delete)) {
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while deleting customer with ID: " + id, e); // Log error lebih jelas
        }
    }
}
