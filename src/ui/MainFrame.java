package ui;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Laundry Gacor");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Yu Gothic", Font.ITALIC, 31));
		lblNewLabel.setBounds(57, 41, 251, 51);
		contentPane.add(lblNewLabel);

		// Tombol Pesanan
		JButton btnPesanan = new JButton("Pesanan");
		btnPesanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame order = new OrderFrame();
				order.setVisible(true);
				order.loadTable();
			}
		});
		btnPesanan.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnPesanan.setBounds(57, 102, 134, 91);
		contentPane.add(btnPesanan);

		// Tombol Layanan
		JButton btnLayanan = new JButton("Layanan");
		btnLayanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServiceFrame service = new ServiceFrame();
				service.setVisible(true);
				service.loadTable();
			}
		});
		btnLayanan.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnLayanan.setBounds(246, 102, 134, 91);
		contentPane.add(btnLayanan);

		// Tombol Pelanggan
		JButton btnPelanggan = new JButton("Pelanggan");
		btnPelanggan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerFrame customer = new CustomerFrame();
				customer.setVisible(true);
				customer.loadTable();
			}
		});
		btnPelanggan.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnPelanggan.setBounds(439, 102, 134, 91);
		contentPane.add(btnPelanggan);

		// Tombol Pengguna
		JButton btnPengguna = new JButton("Pengguna");
		btnPengguna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame user = new UserFrame();
				user.setVisible(true);
				user.loadTable();
			}
		});
		btnPengguna.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnPengguna.setBounds(57, 251, 134, 91);
		contentPane.add(btnPengguna);

		// Tombol Laporan
		JButton btnLaporan = new JButton("Laporan");
		btnLaporan.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnLaporan.setBounds(246, 251, 134, 91);
		contentPane.add(btnLaporan);

		// Tombol Profile
		JButton btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnProfile.setBounds(439, 251, 134, 91);
		contentPane.add(btnProfile);

		// Tombol Keluar
		JButton btnKeluar = new JButton("Keluar");
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		btnKeluar.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnKeluar.setBounds(223, 388, 184, 41);
		contentPane.add(btnKeluar);
	}
}
