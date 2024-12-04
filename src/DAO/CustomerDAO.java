package DAO;

import java.util.List;

import model.Pelanggan;


public interface CustomerDAO {
	void save(Pelanggan pelanggan);
	public List<Pelanggan> show();
	public void delete (String id);
	public void update (Pelanggan pelanggan);
}
