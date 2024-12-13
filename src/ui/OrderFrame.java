package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.OrderRepo;
import model.Order;
import table.TableOrder;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Color;

public class OrderFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTable tableOrder;
    private static OrderDetailFrame orderDetailFrame;

    // Initialize the OrderRepo and List of Orders
    static OrderRepo ord = new OrderRepo();
    static List<Order> ls;

    // Declare Order-related variables
    public String id;
    public String nama;
    public String tanggal;
    public String tanggal_kembali;
    public String status;
    public String total_harga;
    public String pembayaran;
    public String status_bayar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrderFrame frame = new OrderFrame();
                    frame.setVisible(true);
                    frame.loadTable(); // Load the table once the frame is visible
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public OrderFrame() {
        orderDetailFrame = new OrderDetailFrame();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = null;  // Reset the selected ID when clicked outside
            }
        });
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 766, 94);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblDataOrderan = new JLabel("Data Orderan");
        lblDataOrderan.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDataOrderan.setBounds(10, 10, 121, 25);
        panel.add(lblDataOrderan);

        // Button to create a new order
        JButton btnOrder = new JButton("Buat Orderan");
        btnOrder.setBackground(Color.GREEN);
        btnOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newOrderId = buatOrderID();  // Generate a new order ID
                orderDetailFrame.setOrderID(newOrderId); // Set the order ID in the OrderDetailFrame
                orderDetailFrame.setVisible(true);
                orderDetailFrame.getTxtOrderId().setText(newOrderId);
                orderDetailFrame.loadTable();
                orderDetailFrame.loadTableOrderDetail();
            }
        });
        btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnOrder.setBounds(10, 60, 99, 25);
        panel.add(btnOrder);

        // Button to delete an order
        JButton btnHapus = new JButton("Hapus");
        btnHapus.setBackground(Color.RED);
        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {
                    ord.delete(id);  // Delete the selected order by its ID
                    id = null;  // Reset the ID after deletion
                    loadTable();  // Reload the table after deletion
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
                }
            }
        });
        btnHapus.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnHapus.setBounds(576, 60, 85, 25);
        panel.add(btnHapus);

        // Button to edit or view order details
        JButton btnEdit = new JButton("Edit/Detail");
        btnEdit.setBackground(Color.BLUE);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {
                    // Set the selected order details into OrderDetailFrame
                    orderDetailFrame.setOrderID(id);
                    orderDetailFrame.setTxtCostumer(nama);
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        orderDetailFrame.setTanggal(sdf.parse(tanggal));
                        orderDetailFrame.setTanggal_kembali(sdf.parse(tanggal_kembali));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    orderDetailFrame.setStatus(status);
                    orderDetailFrame.setLblTotalHargaShow(total_harga);
                    orderDetailFrame.setBoxPembayaran(pembayaran);
                    orderDetailFrame.setBoxPembayaran_1(status_bayar);

                    orderDetailFrame.setVisible(true);
                    orderDetailFrame.loadTable();
                    orderDetailFrame.loadTableOrderDetail();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan diedit");
                }
            }
        });
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnEdit.setBounds(671, 60, 85, 25);
        panel.add(btnEdit);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 114, 766, 439);
        contentPane.add(scrollPane);

        // Table to display orders
        tableOrder = new JTable();
        tableOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableOrder.getSelectedRow();
                if (selectedRow != -1) {
                    id = tableOrder.getValueAt(selectedRow, 0).toString();  // Get the selected order ID
                    nama = tableOrder.getValueAt(selectedRow, 1).toString();
                    tanggal = tableOrder.getValueAt(selectedRow, 2).toString();
                    tanggal_kembali = tableOrder.getValueAt(selectedRow, 3).toString();
                    status = tableOrder.getValueAt(selectedRow, 4).toString();
                    total_harga = tableOrder.getValueAt(selectedRow, 5).toString();
                    pembayaran = tableOrder.getValueAt(selectedRow, 6).toString();
                    status_bayar = tableOrder.getValueAt(selectedRow, 7).toString();
                }
            }
        });
        scrollPane.setViewportView(tableOrder);
    }

    // Method to load orders into the table
    public static void loadTable() {
        ls = ord.show();
        TableOrder to = new TableOrder(ls);  // Create a new TableOrder with the order list
        tableOrder.setModel(to);  // Set the model for the table
        tableOrder.getTableHeader().setVisible(true);  // Show the table header
    }

    // Method to generate a new unique order ID
    public String buatOrderID() {
        List<Order> existingOrders = ord.show();
        int maxId = 0;
        for (Order order : existingOrders) {
            String orderId = order.getId_order();
            if (orderId.startsWith("TRX-")) {
                int idNumber = Integer.parseInt(orderId.substring(4));  // Extract the numeric part of the ID
                if (idNumber > maxId) {
                    maxId = idNumber;
                }
            }
        }
        maxId++;

        return String.format("TRX-%04d", maxId);  // Format the new order ID as "TRX-XXXX"
    }
}
