package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tugas1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMasukkan;
	private JTextField textarray;
	private JLabel lblhasil;
	private int[] dataArray;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tugas1 frame = new Tugas1();
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
	public Tugas1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMasukkan = new JLabel("Input data coy");
		lblMasukkan.setBounds(112, 53, 131, 13);
		contentPane.add(lblMasukkan);
		
		JLabel lblData = new JLabel("Data :");
		lblData.setBounds(112, 121, 131, 13);
		contentPane.add(lblData);
		
		JLabel lblcek = new JLabel("cek array brp?");
		lblcek.setBounds(21, 201, 91, 13);
		contentPane.add(lblcek);
		
		textMasukkan = new JTextField();
		textMasukkan.setBounds(112, 76, 191, 21);
		contentPane.add(textMasukkan);
		textMasukkan.setColumns(10);
		
		textarray = new JTextField();
		textarray.setColumns(10);
		textarray.setBounds(112, 196, 191, 24);
		contentPane.add(textarray);
		
		JLabel lbldata = new JLabel("");
		lbldata.setBounds(10, 130, 263, 35);
		contentPane.add(lbldata);
		
		JLabel lblhasil = new JLabel("");
		lblhasil.setBounds(10, 242, 263, 35);
		contentPane.add(lblhasil);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ambil input data dan pisahkan dengan koma
				String inputText = textMasukkan.getText();
				String[] inputSplit = inputText.split(",");
				dataArray = new int[inputSplit.length]; // Inisialisasi array

				try {
					// Konversi data menjadi array integer
					for (int i = 0; i < inputSplit.length; i++) {
						dataArray[i] = Integer.parseInt(inputSplit[i].trim());
					}
					lbldata.setText("Data: " + inputText); // Tampilkan data
				} catch (NumberFormatException ex) {
					lbldata.setText("Error: Masukkan hanya angka yang valid!");
				}
			}
		});
		btnSimpan.setBounds(341, 76, 85, 21);
		contentPane.add(btnSimpan);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dataArray == null) {
					lblhasil.setText("Error: Data belum dimasukkan!");
					return;
				}

				try {
					// Ambil indeks dari textarray
					int index = Integer.parseInt(textarray.getText().trim());
					int value = dataArray[index - 1]; // Index user dimulai dari 1
					lblhasil.setText("Hasil: Elemen ke-" + index + " adalah " + value);
				} catch (ArrayIndexOutOfBoundsException ex) {
					lblhasil.setText("Error: Indeks di luar batas array!");
				} catch (NumberFormatException ex) {
					lblhasil.setText("Error: Masukkan indeks yang valid!");
				}
			}
		});
		btnCheck.setBounds(341, 203, 85, 21);
		contentPane.add(btnCheck);
	}
}