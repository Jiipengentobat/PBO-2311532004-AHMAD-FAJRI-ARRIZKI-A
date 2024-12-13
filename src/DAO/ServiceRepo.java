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
import model.Service;

public class ServiceRepo implements ServiceDAO {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(ServiceRepo.class.getName());

    private static final String INSERT = "INSERT INTO service (jenis, status, harga) VALUES (?,?,?);";
    private static final String SELECT = "SELECT * FROM service;";
    private static final String DELETE = "DELETE FROM service WHERE id = ?;";
    private static final String UPDATE = "UPDATE service SET jenis = ?, status = ?, harga = ? WHERE id = ?;";

    public ServiceRepo() {
        this.connection = Database.getInstance().getConnection(); // Menggunakan Singleton
    }

    @Override
    public void save(Service service) {
        try (PreparedStatement st = connection.prepareStatement(INSERT)) {
            st.setString(1, service.getJenis());
            st.setString(2, service.getStatus());
            st.setInt(3, service.getHarga());
            st.executeUpdate();
            logger.info("Service berhasil disimpan: " + service);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat menyimpan Service: " + service, e);
        }
    }

    @Override
    public List<Service> show() {
        List<Service> services = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT)) {

            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getString("id"));
                service.setJenis(rs.getString("jenis"));
                service.setStatus(rs.getString("status"));
                service.setHarga(rs.getInt("harga"));
                services.add(service);
            }
            logger.info("Berhasil mengambil daftar Service.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat mengambil data Service.", e);
        }
        return services;
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement st = connection.prepareStatement(DELETE)) {
            st.setString(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Service dengan ID " + id + " berhasil dihapus.");
            } else {
                logger.warning("Service dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat menghapus Service dengan ID " + id, e);
        }
    }

    @Override
    public void update(Service service) {
        try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
            st.setString(1, service.getJenis());
            st.setString(2, service.getStatus());
            st.setInt(3, service.getHarga());
            st.setString(4, service.getId());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Service dengan ID " + service.getId() + " berhasil diperbarui.");
            } else {
                logger.warning("Service dengan ID " + service.getId() + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat memperbarui Service: " + service, e);
        }
    }
}
