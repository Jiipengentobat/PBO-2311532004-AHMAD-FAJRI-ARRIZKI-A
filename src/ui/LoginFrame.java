package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import error.ValidationException;
import model.User;
import service.LoginService;
import util.ValidationUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JTextField textField_1;
	protected JTextComponent txtPassword;
	protected JTextComponent txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(166, 108, 176, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblLoginFrame = new JLabel("Login Frame");
		lblLoginFrame.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoginFrame.setBounds(212, 44, 84, 29);
		contentPane.add(lblLoginFrame);
		
		lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(166, 93, 62, 13);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(166, 182, 176, 29);
		contentPane.add(textField_1);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(166, 169, 62, 13);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userValue = txtUsername.getText();
			    String passValue = txtPassword.getText();

			    // Create user object
			    User user = new User(userValue, passValue);

			    try {
			        ValidationUtil.validate(user);
			        LoginService loginService = new LoginService();
			        if (loginService.authenticate(user)) {
			            System.out.println("Login successful!");
			            new MainFrame().setVisible(true);
			            dispose();
			        } else {
			            System.out.println("Invalid username or password.");
			            JOptionPane.showMessageDialog(null, "Login Gagal, Invalid username or password.");
			        }
			    } catch (ValidationException | NullPointerException exception) {
			        System.out.println("Data tidak valid: " + exception.getMessage());
			        JOptionPane.showMessageDialog(null, "Login Gagal: " + ((Throwable) exception).getMessage());
			    } finally {
			        System.out.println("Selalu di eksekusi");
			    }
			}

		});
		btnNewButton.setBounds(211, 221, 85, 21);
		contentPane.add(btnNewButton);
	}
}
