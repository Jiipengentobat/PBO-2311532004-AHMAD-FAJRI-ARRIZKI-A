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
import model.OrderDetail;

public class OrderDetailRepo implements OrderDetailDAO {
    private final Connection connection;
    private static final Logger logger = Logger.getLogger(OrderDetailRepo.class.getName());

    private final String insert = "INSERT INTO order_detail (id_order, id_layanan, jumlah, total) VALUES (?,?,?,?);";
    private final String select = "SELECT * FROM order_detail;";
    private final String delete = "DELETE FROM order_detail WHERE id_order_detail = ?;";
    private final String update = "UPDATE order_detail SET id_order=?, id_layanan=?, jumlah=?, total=? WHERE id_order_detail=?;";
    private final String sum = "SELECT SUM(total) AS total FROM order_detail WHERE id_order = ?;";
    private final String selectById = "SELECT * FROM order_detail WHERE id_order = ?;";
    private final String checkOrder = "SELECT COUNT(*) FROM order_detail WHERE id_order = ? AND id_layanan = ?;";

    public OrderDetailRepo() {
        this.connection = Database.getInstance().getConnection(); // Menggunakan Singleton Database
    }

    @Override
    public void save(OrderDetail orderDetail) {
        try (PreparedStatement st = connection.prepareStatement(insert)) {
            st.setString(1, orderDetail.getId_order());
            st.setString(2, orderDetail.getId_layanan());
            st.setInt(3, orderDetail.getJumlah());
            st.setInt(4, orderDetail.getTotal());
            st.executeUpdate();
            logger.info("OrderDetail berhasil disimpan.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat menyimpan OrderDetail.", e);
        }
    }

    public List<OrderDetail> show() {
        List<OrderDetail> list = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(select)) {

            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId_order_detail(rs.getString("id_order_detail"));
                orderDetail.setId_order(rs.getString("id_order"));
                orderDetail.setId_layanan(rs.getString("id_layanan"));
                orderDetail.setJumlah(rs.getInt("jumlah"));
                orderDetail.setTotal(rs.getInt("total"));
                list.add(orderDetail);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat mengambil data OrderDetail.", e);
        }
        return list;
    }

    @Override
    public void update(OrderDetail orderDetail) {
        try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, orderDetail.getId_order());
            st.setString(2, orderDetail.getId_layanan());
            st.setInt(3, orderDetail.getJumlah());
            st.setInt(4, orderDetail.getTotal());
            st.setString(5, orderDetail.getId_order_detail());
            st.executeUpdate();
            logger.info("OrderDetail berhasil diperbarui.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat memperbarui OrderDetail.", e);
        }
    }

    public void delete(String id) {
        try (PreparedStatement st = connection.prepareStatement(delete)) {
            st.setString(1, id);
            st.executeUpdate();
            logger.info("OrderDetail dengan id " + id + " berhasil dihapus.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat menghapus OrderDetail.", e);
        }
    }

    @Override
    public int total(String id_order) {
        int total = 0;
        try (PreparedStatement st = connection.prepareStatement(sum)) {
            st.setString(1, id_order);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat menghitung total OrderDetail.", e);
        }
        return total;
    }

    @Override
    public List<OrderDetail> showById(String id) {
        List<OrderDetail> list = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(selectById)) {
            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId_order_detail(rs.getString("id_order_detail"));
                    orderDetail.setId_order(rs.getString("id_order"));
                    orderDetail.setId_layanan(rs.getString("id_layanan"));
                    orderDetail.setJumlah(rs.getInt("jumlah"));
                    orderDetail.setTotal(rs.getInt("total"));
                    list.add(orderDetail);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat mengambil OrderDetail berdasarkan ID.", e);
        }
        return list;
    }

    @Override
    public boolean cekId_Order(String id_order, String id_layanan) {
        boolean exists = false;
        try (PreparedStatement st = connection.prepareStatement(checkOrder)) {
            st.setString(1, id_order);
            st.setString(2, id_layanan);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saat memeriksa keberadaan OrderDetail.", e);
        }
        return exists;
    }
}
