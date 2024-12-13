package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import model.Pelanggan;
import java.awt.Font;
import java.util.Date;

public class OrderDetailFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCustomerName;
    private JLabel lblCustomerAddress;
    private JLabel lblCustomerPhone;
    private JLabel lblCustomerEmail;

    // Tambahkan komponen untuk input data
    private JTextField txtOrderId;
    private JTextField txtCostumer;
    private JTextField txtTanggal;
    private JTextField txtTanggalKembali;
    private JComboBox<String> boxPembayaran;
    private JComboBox<String> boxPembayaran_1;
    private JLabel lblTotalHargaShow;
    private JLabel lblStatus;

    // Method untuk set data customer
    public void setCustomer(Pelanggan pelanggan) {
        lblCustomerName.setText("Nama: " + pelanggan.getNama());
        lblCustomerAddress.setText("Alamat: " + pelanggan.getAlamat());
        lblCustomerPhone.setText("Telepon: " + pelanggan.getHp());
        lblCustomerEmail.setText("Email: " + pelanggan.getEmail());
    }

    // Method untuk mengatur ID Order
    public void setOrderID(String orderID) {
        txtOrderId.setText(orderID);
    }

    // Getter untuk txtOrderId
    public JTextField getTxtOrderId() {
        return txtOrderId;
    }

    // Method untuk mengatur Nama Costumer
    public void setTxtCostumer(String nama) {
        txtCostumer.setText(nama);
    }

    // Method untuk mengatur Tanggal
    public void setTanggal(Date tanggal) {
        txtTanggal.setText(tanggal.toString());
    }

    // Method untuk mengatur Tanggal Kembali
    public void setTanggal_kembali(Date tanggalKembali) {
        txtTanggalKembali.setText(tanggalKembali.toString());
    }

    // Method untuk mengatur Status
    public void setStatus(String status) {
        lblStatus.setText(status);
    }

    // Method untuk mengatur Total Harga
    public void setLblTotalHargaShow(String totalHarga) {
        lblTotalHargaShow.setText("Total Harga: " + totalHarga);
    }

    // Method untuk mengatur Pembayaran
    public void setBoxPembayaran(String pembayaran) {
        boxPembayaran.setSelectedItem(pembayaran);
    }

    // Method untuk mengatur Status Pembayaran
    public void setBoxPembayaran_1(String statusBayar) {
        boxPembayaran_1.setSelectedItem(statusBayar);
    }

    // Method untuk mengatur tabel (untuk diimplementasikan lebih lanjut)
    public void loadTable() {
        // Implementasikan logika pemuatan data tabel jika diperlukan
    }

    // Method untuk mengatur tabel order detail (untuk diimplementasikan lebih lanjut)
    public void loadTableOrderDetail() {
        // Implementasikan logika pemuatan data order detail jika diperlukan
    }

    /**
     * Create the frame.
     */
    public OrderDetailFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 500);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Inisialisasi komponen-komponen UI
        lblCustomerName = new JLabel("Nama: ");
        lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCustomerName.setBounds(20, 30, 350, 30);
        contentPane.add(lblCustomerName);

        lblCustomerAddress = new JLabel("Alamat: ");
        lblCustomerAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCustomerAddress.setBounds(20, 70, 350, 30);
        contentPane.add(lblCustomerAddress);

        lblCustomerPhone = new JLabel("Telepon: ");
        lblCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCustomerPhone.setBounds(20, 110, 350, 30);
        contentPane.add(lblCustomerPhone);

        lblCustomerEmail = new JLabel("Email: ");
        lblCustomerEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCustomerEmail.setBounds(20, 150, 350, 30);
        contentPane.add(lblCustomerEmail);

        // Menambahkan komponen input untuk orderan
        txtOrderId = new JTextField();
        txtOrderId.setBounds(150, 200, 200, 30);
        contentPane.add(txtOrderId);

        txtCostumer = new JTextField();
        txtCostumer.setBounds(150, 240, 200, 30);
        contentPane.add(txtCostumer);

        txtTanggal = new JTextField();
        txtTanggal.setBounds(150, 280, 200, 30);
        contentPane.add(txtTanggal);

        txtTanggalKembali = new JTextField();
        txtTanggalKembali.setBounds(150, 320, 200, 30);
        contentPane.add(txtTanggalKembali);

        // Menambahkan ComboBox untuk pembayaran
        boxPembayaran = new JComboBox<>(new String[] {"Tunai", "Kartu Kredit", "Transfer Bank"});
        boxPembayaran.setBounds(150, 360, 200, 30);
        contentPane.add(boxPembayaran);

        // Menambahkan ComboBox untuk status pembayaran
        boxPembayaran_1 = new JComboBox<>(new String[] {"Lunas", "Belum Lunas"});
        boxPembayaran_1.setBounds(150, 400, 200, 30);
        contentPane.add(boxPembayaran_1);

        // Label untuk total harga
        lblTotalHargaShow = new JLabel("Total Harga: ");
        lblTotalHargaShow.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTotalHargaShow.setBounds(20, 440, 350, 30);
        contentPane.add(lblTotalHargaShow);

        // Label untuk status
        lblStatus = new JLabel("Status: ");
        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblStatus.setBounds(20, 480, 350, 30);
        contentPane.add(lblStatus);
    }
}
