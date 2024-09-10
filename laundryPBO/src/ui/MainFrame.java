package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
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
		
		JButton btnService = new JButton("Service");
		btnService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnService.setBounds(246, 102, 134, 91);
		contentPane.add(btnService);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOrder.setBounds(57, 102, 134, 91);
		contentPane.add(btnOrder);
		
		JButton btncustomer = new JButton("Customer");
		btncustomer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncustomer.setBounds(439, 102, 134, 91);
		contentPane.add(btncustomer);
		
		JButton btnUser = new JButton("User");
		btnUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUser.setBounds(57, 251, 134, 91);
		contentPane.add(btnUser);
		
		JButton btnLaporan = new JButton("Laporan");
		btnLaporan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLaporan.setBounds(246, 251, 134, 91);
		contentPane.add(btnLaporan);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfile.setBounds(439, 251, 134, 91);
		contentPane.add(btnProfile);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desain frame = new Desain();
                frame.setVisible(true);
                dispose();
			}
		});
		btnLogOut.setBounds(223, 388, 184, 41);
		contentPane.add(btnLogOut);
		
		
		
		JLabel lblNewLabel = new JLabel("Laundry Gacor ");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Yu Gothic", Font.ITALIC, 31));
		lblNewLabel.setBounds(57, 41, 251, 51);
		contentPane.add(lblNewLabel);
	}

}
