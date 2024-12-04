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


public class CustomerRepo implements CustomerDAO{
	private Connection connection;
	final String insert = "INSERT INTO customer (nama, alamat, nohp) VALUES (?,?,?);";
	final String select = "SELECT * FROM customer;" ;
	final String delete = "DELETE FROM customer WHERE id = ?;";
	final String update = "UPDATE customer SET nama=?, alamat=?, nohp=? WHERE id=?;";
	
	public CustomerRepo() {
		connection = Database.koneksi();
		}
	
	@Override
	public void save(Pelanggan pelanggan) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, pelanggan.getNama());
			st.setString(2, pelanggan.getAlamat());
			st.setString(3, pelanggan.getNohp());
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
	
	public List<Pelanggan> show(){
		List<Pelanggan> ls = null;
		try {
			ls = new ArrayList<Pelanggan>();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(select);
			while(rs.next()) {
				Pelanggan pelanggan = new Pelanggan();
				pelanggan.setId(rs.getString("id"));
				pelanggan.setNama(rs.getString("nama"));
				pelanggan.setAlamat(rs.getString("alamat"));
				pelanggan.setNohp(rs.getString("nohp"));
				ls.add(pelanggan);
			}
		}catch(SQLException e) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return ls;
	}
	
	@Override
	public void update (Pelanggan pelanggan) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, pelanggan.getNama());
			st.setString(2, pelanggan.getAlamat());
			st.setString(3, pelanggan.getNohp());
			st.setString(4, pelanggan.getId());
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
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
	
	
}
