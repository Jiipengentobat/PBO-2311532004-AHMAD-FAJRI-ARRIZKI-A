package DAO;

import java.util.List;
import model.Pelanggan;

public interface CustomerDAO {
	public void save(Pelanggan cs);
	public void update(Pelanggan cs);
	public void delete(String id);
	public List<Pelanggan> show();
}
