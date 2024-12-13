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
import model.User;

public class UserRepo implements UserDAO {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(UserRepo.class.getName());

    private static final String INSERT = "INSERT INTO user (name, username, password) VALUES (?, ?, ?);";
    private static final String SELECT = "SELECT * FROM user;";
    private static final String DELETE = "DELETE FROM user WHERE id = ?;";
    private static final String UPDATE = "UPDATE user SET name = ?, username = ?, password = ? WHERE id = ?;";

    public UserRepo() {
        // Menggunakan Database.getInstance() untuk mendapatkan koneksi
        this.connection = Database.getInstance().getConnection();
        if (this.connection == null) {
            logger.severe("Koneksi ke database gagal!");
            throw new RuntimeException("Gagal membuat koneksi ke database");
        } else {
            logger.info("Koneksi ke database berhasil!");
        }
    }

    @Override
    public void save(User user) {
        try (PreparedStatement st = connection.prepareStatement(INSERT)) {
            st.setString(1, user.getNama());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.executeUpdate();
            logger.info("User berhasil disimpan: " + user);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat menyimpan User: " + user, e);
        }
    }

    @Override
    public List<User> show() {
        List<User> users = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT)) {

            while (rs.next()) {
                User user = new User(null, null);
                user.setId(rs.getString("id"));
                user.setNama(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            logger.info("Berhasil mengambil daftar User.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat mengambil data User.", e);
        }
        return users;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
            st.setString(1, user.getNama());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.setString(4, user.getId());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("User dengan ID " + user.getId() + " berhasil diperbarui.");
            } else {
                logger.warning("User dengan ID " + user.getId() + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat memperbarui User: " + user, e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement st = connection.prepareStatement(DELETE)) {
            st.setString(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("User dengan ID " + id + " berhasil dihapus.");
            } else {
                logger.warning("User dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat menghapus User dengan ID " + id, e);
        }
    }
}
