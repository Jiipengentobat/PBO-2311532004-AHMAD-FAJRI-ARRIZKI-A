package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Pelanggan;

public class TableCustomer extends AbstractTableModel {
	List<Pelanggan> ls;
	private String[] columnNames = {"ID", "Nama", "Alamat", "Nohp"};
	public TableCustomer(List<Pelanggan> ls) {
		this.ls = ls;
	}
	
	@Override
	public int getRowCount() {
		return ls.size();
	}
	
	
	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override
	public String getColumnName(int colomn) {
		return columnNames[colomn];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ls.get(rowIndex).getId();
		case 1:
			return ls.get(rowIndex).getNama();
		case 2:
			return ls.get(rowIndex).getAlamat();
		case 3:
			return ls.get(rowIndex).getNohp();
		default:
			return null;
		}
	}
	public Pelanggan getCostumerAt(int rowIndex) {

        return ls.get(rowIndex);

    }
}
