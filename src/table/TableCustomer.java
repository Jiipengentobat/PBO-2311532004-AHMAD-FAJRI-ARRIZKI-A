package table;

import model.Pelanggan;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableCustomer extends AbstractTableModel {
    private List<Pelanggan> customers;
    private String[] columnNames = {"ID", "Nama", "Alamat", "Telepon", "Email"};

    public TableCustomer(List<Pelanggan> customers) {
        this.customers = customers;
    }

    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pelanggan customer = customers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return customer.getId();
            case 1:
                return customer.getNama();
            case 2:
                return customer.getAlamat();
            case 3:
                return customer.getHp();
            case 4:
                return customer.getEmail();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Menambahkan metode getCustomerAt untuk mendapatkan pelanggan berdasarkan baris
    public Pelanggan getCustomerAt(int rowIndex) {
        return customers.get(rowIndex);
    }
}
