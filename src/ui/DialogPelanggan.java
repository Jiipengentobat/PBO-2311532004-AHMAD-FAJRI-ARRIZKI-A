package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import DAO.CustomerRepo;
import model.Pelanggan;
import table.TableCustomer;
import ui.OrderDetailFrame;

public class DialogPelanggan extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable tableCustomer;
    private OrderDetailFrame orderDetailFrame;
    CustomerRepo cst = CustomerRepo.getInstance(); // Gunakan instance tunggal dari CustomerRepo
    List<Pelanggan> ls;

    public static void main(String[] args) {
        try {
            DialogPelanggan dialog = new DialogPelanggan();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.loadTable(); // Load table setelah dialog terbuka
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Memuat tabel pelanggan
    public void loadTable() {
        ls = cst.show(); // Ambil data pelanggan dari repo
        TableCustomer tc = new TableCustomer(ls); // Buat model tabel dengan data pelanggan
        tableCustomer.setModel(tc); // Atur model tabel
        tableCustomer.getTableHeader().setVisible(true); // Menampilkan header tabel
    }

    public DialogPelanggan() {
        setBounds(100, 100, 600, 420);
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 586, 352);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 566, 332);
        contentPanel.add(scrollPane);

        tableCustomer = new JTable();
        tableCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableCustomer.getSelectedRow();
                if (selectedRow != -1) {
                    // Mengambil pelanggan yang dipilih dari model tabel
                    Pelanggan selectedCustomer = ((TableCustomer) tableCustomer.getModel()).getCustomerAt(selectedRow);
                    // Mengirim data pelanggan ke OrderDetailFrame
                    orderDetailFrame.setCustomer(selectedCustomer);
                    DialogPelanggan.this.dispose(); // Tutup dialog setelah pemilihan
                }
            }
        });
        scrollPane.setViewportView(tableCustomer);
    }

    // Method untuk mengatur OrderDetailFrame
    public void setOrderDetailFrame(OrderDetailFrame orderDetailFrame) {
        this.orderDetailFrame = orderDetailFrame;
    }
}
