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
import model.Order;


public class OrderRepo implements OrderDAO{
	private Connection connection;
	final String select = "SELECT id_order, cust.id AS id_customer, cust.nama AS nama, tanggal, tanggal_kembali, status, total_harga, pembayaran, status_bayar "
			+ "FROM tabel_order AS tabel JOIN customer AS cust ON (tabel.id_customer = cust.id);" ;
	final String delete = "DELETE FROM tabel_order WHERE id_order = ?;";
	
	
	public OrderRepo() {
		connection = Database.koneksi();
		}
	
			
	public List<Order> show(){
		List<Order> ls = null;
		try {
			ls = new ArrayList<Order>();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(select);
			while(rs.next()) {
				Order order = new Order();
				order.setId_order(rs.getString("id_order"));
				order.setId_customer(rs.getInt("id_customer"));
				order.setNama(rs.getString("nama"));
				order.setTanggal(rs.getString("tanggal"));
				order.setTanggal_kembali(rs.getString("tanggal_kembali"));
				order.setStatus(rs.getString("status"));
				order.setTotal_harga(rs.getInt("total_harga"));
				order.setPembayaran(rs.getString("pembayaran"));
				order.setStatus_bayar(rs.getString("status_bayar"));
				ls.add(order);
			}
		}catch(SQLException e) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return ls;
	}
	
	
	
	public void delete(String id) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(delete);
			st.setString(1, id);
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void save(Order order) {
		String save = "INSERT INTO tabel_order (id_order, id_customer, tanggal, tanggal_kembali, status, "
				+ "total_harga, pembayaran, status_bayar) "
                + "VALUES (?, (SELECT id FROM customer WHERE nama = ? LIMIT 1), ?, ?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "tanggal = VALUES(tanggal), "
                + "tanggal_kembali = VALUES(tanggal_kembali), "
                + "status = VALUES(status), "
                + "total_harga = VALUES(total_harga), "
                + "pembayaran = VALUES(pembayaran), "
                + "status_bayar = VALUES(status_bayar);";
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(save);
			st.setString(1, order.getId_order());
	        st.setString(2, order.getNama()); // Assuming you have a method to get the customer's name
	        st.setString(3, order.getTanggal());
	        st.setString(4, order.getTanggal_kembali());
	        st.setString(5, order.getStatus());
	        st.setInt(6, order.getTotal_harga()); // Assuming total_harga is an int
	        st.setString(7, order.getPembayaran());
	        st.setString(8, order.getStatus_bayar());
			st.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
}
