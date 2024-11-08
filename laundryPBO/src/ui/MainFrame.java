package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

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
		
		JButton btnNewButton = new JButton("Pesanan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame order = new OrderFrame();
				order.setVisible(true);
				order.loadTable();
				
			}
		});
		btnNewButton.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnNewButton.setBounds(57, 102, 134, 91);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Layanan");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServiceFrame service = new ServiceFrame();
				service.setVisible(true);
				service.loadTable();
			}
		});
		btnNewButton_1.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnNewButton_1.setBounds(246, 102, 134, 91);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Pelanggan");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerFrame costumer = new CustomerFrame();
				costumer.setVisible(true);
				costumer.loadTable();
				
			}
		});
		btnNewButton_1_1.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(439, 102, 134, 91);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("Pengguna");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame user = new UserFrame();
				user.setVisible(true);
				user.loadTable();
			}
			
		});
		btnNewButton_2.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnNewButton_2.setBounds(57, 251, 134, 91);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1_2 = new JButton("Laporan");
		btnNewButton_1_2.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnNewButton_1_2.setBounds(246, 251, 134, 91);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_1_1 = new JButton("Profile");
		btnNewButton_1_1_1.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnNewButton_1_1_1.setBounds(439, 251, 134, 91);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Keluar");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_1.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnNewButton_2_1.setBounds(223, 388, 184, 41);
		contentPane.add(btnNewButton_2_1);
		
		
	}

}
