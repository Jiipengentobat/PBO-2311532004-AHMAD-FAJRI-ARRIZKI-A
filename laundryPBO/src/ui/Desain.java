package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Desain extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField txtPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Desain frame = new Desain();
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
    public Desain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 495, 489);
        contentPane = new JPanel();
        contentPane.setForeground(Color.RED);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("username");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel.setBounds(134, 164, 117, 13);
        contentPane.add(lblNewLabel);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(134, 180, 181, 19);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("password");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel_1.setBounds(134, 217, 96, 21);
        contentPane.add(lblNewLabel_1);
        
        txtPassword = new JTextField();
        txtPassword.setBounds(134, 240, 181, 19);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);
        
        JButton btnNewButton = new JButton("login");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(User.login(txtUsername.getText(), txtPassword.getText())) {
                    JOptionPane.showMessageDialog(null,"berhasil login ya adik adik");
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                    dispose();
                    
                }else {
                    JOptionPane.showMessageDialog(null, "gagal login kocak,skill isu");
                    HalamanSalah frame = new HalamanSalah();
                    frame.setVisible(true);
                    dispose();
                }
            }
        });
        btnNewButton.setBounds(187, 298, 85, 21);
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel_2 = new JLabel("malas nyuci?? antar kesini");
        lblNewLabel_2.setFont(new Font("STXinwei", Font.BOLD | Font.ITALIC, 15));
        lblNewLabel_2.setBounds(134, 86, 181, 29);
        contentPane.add(lblNewLabel_2);
    }

}