package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ServiceRepo;
import DAO.UserRepo;
import model.Service;
import model.User;
import table.TableService;
import table.TableUser;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ServiceFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtJenis;
	private JTextField txtHarga;
	private JTable tableService;
	private JComboBox boxStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceFrame frame = new ServiceFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void reset() {
		txtJenis.setText("");
		boxStatus.setSelectedIndex(0);
		txtHarga.setText("");
	}

	ServiceRepo srv = new ServiceRepo();
	List<Service> ls;
	public String id;
	
	public void loadTable() {
		ls = srv.show();
		TableService ts = new TableService(ls);
		tableService.setModel(ts);
		tableService.getTableHeader().setVisible(true);
	}
	
	public ServiceFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 766, 260);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblJenis = new JLabel("Jenis");
		lblJenis.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblJenis.setBounds(70, 25, 45, 25);
		panel.add(lblJenis);
		
		txtJenis = new JTextField();
		txtJenis.setBounds(165, 31, 505, 19);
		panel.add(txtJenis);
		txtJenis.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatus.setBounds(70, 75, 76, 25);
		panel.add(lblStatus);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHarga.setBounds(70, 122, 76, 25);
		panel.add(lblHarga);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		txtHarga.setBounds(165, 128, 505, 19);
		panel.add(txtHarga);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.GREEN);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Service service = new Service();
					service.setJenis(txtJenis.getText());
					service.setStatus(boxStatus.getSelectedItem().toString());
					service.setHarga(Integer.parseInt(txtHarga.getText()));
					srv.save(service);
					reset();
					loadTable();
				
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(165, 193, 85, 40);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(Color.BLUE);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					Service service = new Service();
					service.setJenis(txtJenis.getText());
					service.setStatus(boxStatus.getSelectedItem().toString());
					service.setHarga(Integer.parseInt(txtHarga.getText()));
					service.setId(id);
					srv.update(service);
					reset();
					loadTable();
				}
				else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan diupdate");
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(271, 193, 85, 40);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.RED);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					srv.delete(id);
					reset();
					loadTable();
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(383, 193, 85, 40);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.YELLOW);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(585, 193, 85, 40);
		panel.add(btnCancel);
		
		boxStatus = new JComboBox();
		boxStatus.setModel(new DefaultComboBoxModel(new String[] {"Aktif", "Tidak Aktif"}));
		boxStatus.setBounds(165, 79, 119, 21);
		panel.add(boxStatus);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 280, 766, 273);
		contentPane.add(scrollPane);
		
		tableService = new JTable();
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableService.getValueAt(tableService.getSelectedRow(), 0).toString();
				txtJenis.setText(tableService.getValueAt(tableService.getSelectedRow(), 1).toString());
				boxStatus.setSelectedItem(tableService.getValueAt(tableService.getSelectedRow(), 2));
				//txtStatus.setText(tableService.getValueAt(tableService.getSelectedRow(), 2).toString());
				txtHarga.setText(tableService.getValueAt(tableService.getSelectedRow(), 3).toString());
			}
		});
		scrollPane.setViewportView(tableService);
		
		
		
		
		
	}
}
