package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import DAO.CustomerRepo;
import model.CustomerBuilder;
import model.Pelanggan;
import table.TableCustomer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CustomerFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCustomerName;
    private JTextField txtCustomerAddress;
    private JTextField txtCustomerPhone;
    private JTextField txtCustomerEmail;
    private JTable tableCustomers;

    private List<Pelanggan> ls;
    private CustomerRepo customerRepo = CustomerRepo.getInstance(); // Ensure singleton is used

    private String selectedCustomerId = null; // To store selected customer ID

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerFrame frame = new CustomerFrame();
                    frame.setVisible(true);
                    frame.loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Load data into the table
    public void loadTable() {
        ls = customerRepo.show();
        TableCustomer tc = new TableCustomer(ls);
        tableCustomers.setModel(tc);
        tableCustomers.getTableHeader().setVisible(true);
    }

    // Reset form fields and selected customer ID
    public void reset() {
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerPhone.setText("");
        txtCustomerEmail.setText("");
        selectedCustomerId = null; // Reset selected customer ID
    }

    public CustomerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Panel for form inputs
        JPanel panel = new JPanel();
        panel.setBounds(6, 10, 576, 200);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblName = new JLabel("Nama");
        lblName.setBounds(10, 20, 100, 30);
        panel.add(lblName);
        
        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(120, 20, 200, 30);
        panel.add(txtCustomerName);
        
        JLabel lblAddress = new JLabel("Alamat");
        lblAddress.setBounds(10, 60, 100, 30);
        panel.add(lblAddress);
        
        txtCustomerAddress = new JTextField();
        txtCustomerAddress.setBounds(120, 60, 200, 30);
        panel.add(txtCustomerAddress);

        JLabel lblPhone = new JLabel("Telepon");
        lblPhone.setBounds(10, 100, 100, 30);
        panel.add(lblPhone);

        txtCustomerPhone = new JTextField();
        txtCustomerPhone.setBounds(120, 100, 200, 30);
        panel.add(txtCustomerPhone);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(10, 140, 100, 30);
        panel.add(lblEmail);

        txtCustomerEmail = new JTextField();
        txtCustomerEmail.setBounds(120, 140, 200, 30);
        panel.add(txtCustomerEmail);

        // Table panel
        JPanel panelTable = new JPanel();
        panelTable.setBounds(6, 250, 576, 200);
        contentPane.add(panelTable);
        panelTable.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 529, 170);
        panelTable.add(scrollPane);

        tableCustomers = new JTable();
        scrollPane.setViewportView(tableCustomers);
        tableCustomers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableCustomers.getSelectedRow();
                if (selectedRow != -1) {
                    // Memanggil metode yang sudah diperbaiki di TableCustomer
                    Pelanggan selectedCustomer = ((TableCustomer) tableCustomers.getModel()).getCustomerAt(selectedRow);
                    selectedCustomerId = selectedCustomer.getId(); // Store selected customer ID
                    // Set the selected customer data to the text fields
                    txtCustomerName.setText(selectedCustomer.getNama());
                    txtCustomerAddress.setText(selectedCustomer.getAlamat());
                    txtCustomerPhone.setText(selectedCustomer.getHp());
                    txtCustomerEmail.setText(selectedCustomer.getEmail());
                }
            }
        });

        // Save button
        JButton btnSave = new JButton("Simpan");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pelanggan pelanggan = new CustomerBuilder()
                    .setNama(txtCustomerName.getText())
                    .setAlamat(txtCustomerAddress.getText())
                    .setHp(txtCustomerPhone.getText())
                    .setEmail(txtCustomerEmail.getText())
                    .build();

                if (selectedCustomerId == null) {
                    // If no customer is selected, save as new customer
                    customerRepo.save(pelanggan);
                } else {
                    // If a customer is selected, update their data
                    pelanggan.setId(selectedCustomerId); // Set the ID for updating
                    customerRepo.update(pelanggan); // Update using the customer object
                }
                reset();
                loadTable();
            }
        });
        btnSave.setBounds(120, 460, 100, 30);
        contentPane.add(btnSave);

        // Reset button (optional)
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnReset.setBounds(240, 460, 100, 30);
        contentPane.add(btnReset);
    }
}
