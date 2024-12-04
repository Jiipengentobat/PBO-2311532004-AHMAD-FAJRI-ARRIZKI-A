package ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.OrderDetailRepo;
import DAO.OrderRepo;
import DAO.ServiceRepo;
import model.Pelanggan;
import model.Order;
import model.OrderDetail;
import model.Service;
import table.TableOrderDetail;
import table.TableService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class OrderDetailFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtOrderID;
	private JTextField txtHargaKg;
	private JTextField txtJumlah;
	private JTextField txtTotal;
	private JDateChooser tanggal;
	private JDateChooser tanggal_kembali;
	private JComboBox boxStatus;
	private JLabel lblHargaShow;
	private JComboBox boxPembayaran;
	private JComboBox boxStatusBayar;
	private JTable tableService;
	private JTable tableOrderDetail;
	private JTextField txtCostumer;

	
	//import DAO
	OrderRepo ord_repo = new OrderRepo();
	List<Order> ls_opo;
	
	ServiceRepo srv = new ServiceRepo();
	List<Service> ls_srv;
	
	OrderDetailRepo ord_detail = new OrderDetailRepo();
	List<OrderDetail> ls_ord;
	
	//Deklarasi Variabel
	public String id;
	public String id_ord;
	public static String id_order;
	public int total_harga;
	public String tgl;
	public String tgl_kbl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetailFrame frame = new OrderDetailFrame();
					frame.setVisible(true);
					frame.loadTable();
					frame.loadTableOrderDetail();
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//method-method yang digunakan
	public void reset() {
		txtHargaKg.setText("");
		txtJumlah.setText("");
		txtTotal.setText("");
	}
	
	
	public void resetOrder() {
		txtOrderID.setText("");
		
	}
	
	
	public static void setId_order(String orderId) {
	    id_order = orderId; 

	}
	public void loadTable() {
		ls_srv = srv.show();
		TableService ts = new TableService(ls_srv);
		tableService.setModel(ts);
		tableService.getTableHeader().setVisible(true);
	}
	
	public void loadTableOrderDetail() {
		ls_ord = ord_detail.showById(id_order);
		TableOrderDetail tod = new TableOrderDetail(ls_ord);
		tableOrderDetail.setModel(tod);
		tableOrderDetail.getTableHeader().setVisible(true);
		updateTotalHarga();
		
	}
	
	public void updateTotalHarga() {
        int totalHarga = ord_detail.total(id_order);
        total_harga = totalHarga;
    }
	
	public void setCostumer(Pelanggan pelanggan) {
	    txtCostumer.setText(pelanggan.getNama()); 
	}
	
	public void setOrderID(String id_order) {
	    txtOrderID.setText(id_order); 
	}
	
	public JTextField getTxtOrderId() {
        return txtOrderID; 
    }
	
	public void setTxtCostumer(String cust) {
		txtCostumer.setText(cust);
	}
	
	
	
	public void resetAll() {
		id_ord = null;
		id_order = null;
		total_harga = 0;
		tgl = null;
		tgl_kbl = null;
		setTxtCostumer("");
		setTanggal(null);
		setTanggal_kembali(null);
		setLblTotalHargaShow("");
	}
	public void setTanggal(Date date) {
        tanggal.setDate(date); 
    }
	public void setTanggal_kembali(Date date) {
        tanggal_kembali.setDate(date); 
    }
	
	public void setStatus(String status) {
		boxStatus.setSelectedItem(status);
	}
	public void setLblTotalHargaShow(String total) {
		lblHargaShow.setText("Rp. " + total);
	}
	public void setBoxPembayaran(String pembayaran) {
		boxPembayaran.setSelectedItem(pembayaran);
	}
	public void setBoxPembayaran_1(String pembayaran) {
		boxStatusBayar.setSelectedItem(pembayaran);
	}
	/**
	 * Create the frame.
	 */
	public OrderDetailFrame() {
		setBackground(new Color(246, 246, 246));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246, 246, 246));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrderId = new JLabel("Order ID");
		lblOrderId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOrderId.setBounds(20, 22, 92, 25);
		contentPane.add(lblOrderId);
		
		txtOrderID = new JTextField();
		txtOrderID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				id_order = txtOrderID.getText();
			}
		});
		txtOrderID.setBounds(20, 48, 170, 19);
		contentPane.add(txtOrderID);
		txtOrderID.setColumns(10);
		txtOrderID.setEnabled(false);
		
		JLabel lblPelanggan = new JLabel("Pelanggan");
		lblPelanggan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPelanggan.setBounds(20, 75, 92, 25);
		contentPane.add(lblPelanggan);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTanggal.setBounds(20, 133, 81, 25);
		contentPane.add(lblTanggal);
		
		JLabel lblTanggalPengambilan = new JLabel("Tanggal Pengambilan");
		lblTanggalPengambilan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTanggalPengambilan.setBounds(20, 188, 170, 25);
		contentPane.add(lblTanggalPengambilan);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatus.setBounds(20, 239, 81, 25);
		contentPane.add(lblStatus);
		
		boxStatus = new JComboBox();
		boxStatus.setModel(new DefaultComboBoxModel(new String[] {"Progress", "Selesai", "Diambil"}));
		boxStatus.setBounds(20, 266, 170, 21);
		contentPane.add(boxStatus);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotal.setBounds(20, 297, 53, 25);
		contentPane.add(lblTotal);
		
		
		
		JLabel lblPembayaran = new JLabel("Pembayaran");
		lblPembayaran.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPembayaran.setBounds(20, 356, 103, 25);
		contentPane.add(lblPembayaran);
		
		boxPembayaran = new JComboBox();
		boxPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Tunai", "Non-Tunai"}));
		boxPembayaran.setBounds(20, 380, 170, 21);
		contentPane.add(boxPembayaran);
		
		JLabel lblStatusPembayaran = new JLabel("Status Pembayaran");
		lblStatusPembayaran.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatusPembayaran.setBounds(20, 411, 148, 25);
		contentPane.add(lblStatusPembayaran);
		
		boxStatusBayar = new JComboBox();
		boxStatusBayar.setModel(new DefaultComboBoxModel(new String[] {"Lunas", "Belum Lunas"}));
		boxStatusBayar.setBounds(20, 436, 170, 21);
		contentPane.add(boxStatusBayar);
		
		JLabel lblLayanan = new JLabel("Layanan");
		lblLayanan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLayanan.setBounds(220, 22, 103, 25);
		contentPane.add(lblLayanan);
		
		JLabel lblHargakg = new JLabel("Harga/Kg");
		lblHargakg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHargakg.setBounds(220, 223, 103, 25);
		contentPane.add(lblHargakg);
		
		JLabel lblId_layanan = new JLabel("");
		lblId_layanan.setEnabled(false);
		lblId_layanan.setVisible(false);
		lblId_layanan.setBounds(400, 248, 326, 13);
		contentPane.add(lblId_layanan);
		
		txtHargaKg = new JTextField();
		txtHargaKg.setColumns(10);
		txtHargaKg.setBounds(220, 250, 170, 19);
		contentPane.add(txtHargaKg);
		txtHargaKg.setEnabled(false);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblJumlah.setBounds(220, 279, 103, 25);
		contentPane.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String jumlahText = txtJumlah.getText();
		        String hargaKgText = txtHargaKg.getText();
		        if (!jumlahText.isEmpty() && !hargaKgText.isEmpty()) {
		            try {
		                int jumlah = Integer.parseInt(jumlahText);
		                int hargaKg = Integer.parseInt(hargaKgText);
		                int totalharga = jumlah * hargaKg;
		                txtTotal.setText(String.valueOf(totalharga));
		            } catch (NumberFormatException ex) {
		                txtTotal.setText("0"); 
		            }
		        } else {
		            txtTotal.setText("0"); 
		        }
			}
		});
		txtJumlah.setColumns(10);
		txtJumlah.setBounds(220, 303, 170, 19);
		contentPane.add(txtJumlah);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotal_1.setBounds(400, 279, 103, 25);
		contentPane.add(lblTotal_1);
		
		JLabel lblId_order = new JLabel("");
		lblId_order.setEnabled(false);
		lblId_order.setVisible(false);
		lblId_order.setBounds(400, 248, 326, 13);
		contentPane.add(lblId_order);
		
		lblHargaShow = new JLabel("Rp. " + ord_detail.total(txtOrderID.getText()));
		lblHargaShow.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHargaShow.setBounds(20, 321, 170, 25);
		contentPane.add(lblHargaShow);
		updateTotalHarga();
		lblHargaShow.setText("Rp. " + total_harga);
		
		tanggal = new JDateChooser();
		tanggal.setBounds(20, 159, 170, 19);
		contentPane.add(tanggal);
		tanggal.getDateEditor().addPropertyChangeListener("date", evt -> {
		    if (tanggal.getDate() != null) {
		        SimpleDateFormat sdf_tanggal = new SimpleDateFormat("yyyy-MM-dd");
		        tgl = sdf_tanggal.format(tanggal.getDate());
		    }

		});
		
		tanggal_kembali = new JDateChooser();
		tanggal_kembali.setBounds(20, 215, 170, 19);
		contentPane.add(tanggal_kembali);
		tanggal_kembali.getDateEditor().addPropertyChangeListener("date", evt -> {
		    if (tanggal_kembali.getDate() != null) {
		    	SimpleDateFormat sdf_tanggal_kembali = new SimpleDateFormat("yyy-MM-dd");
		        tgl_kbl = sdf_tanggal_kembali.format(tanggal_kembali.getDate());
		    }
		});
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(400, 303, 170, 19);
		contentPane.add(txtTotal);
		txtTotal.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 50, 556, 165);
		contentPane.add(scrollPane);
		
		tableService = new JTable();
		tableService.getTableHeader().setVisible(true);
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tableService.getSelectedRow();

		        if (selectedRow != -1) {
		            String idLayanan = tableService.getValueAt(selectedRow, 0).toString();
		            txtHargaKg.setText(tableService.getValueAt(selectedRow, 3).toString());
		            lblId_layanan.setText(idLayanan);
		            if (ord_detail.cekId_Order(id_order, idLayanan)) {
		                JOptionPane.showMessageDialog(null, "Service sudah ada di order detail.");
		            } else { 
		            }
		        }
			}
		});
		scrollPane.setViewportView(tableService);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBackground(Color.GREEN);
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order != null) {
					if (id_order != null) {
			            String idLayanan = lblId_layanan.getText();
			            if (ord_detail.cekId_Order(id_order, idLayanan)) {
			                JOptionPane.showMessageDialog(null, "Layanan ini sudah ada", "Error", JOptionPane.ERROR_MESSAGE);
			            } else {
			                String jumlahText = txtJumlah.getText();
			                String totalText = txtTotal.getText();

			                if (jumlahText.isEmpty()) {
			                    JOptionPane.showMessageDialog(null, "Jumlah kosong.", "Error", JOptionPane.ERROR_MESSAGE);
			                    return; 
			                }
			                
			                if (totalText.isEmpty()) {
			                    JOptionPane.showMessageDialog(null, "Total kosong.", "Error", JOptionPane.ERROR_MESSAGE);
			                    return;
			                }
			                try {
			                    int jumlah = Integer.parseInt(jumlahText);
			                    int total = Integer.parseInt(totalText);
			                    OrderDetail orderdetail = new OrderDetail();
			                    orderdetail.setId_order(id_order);
			                    orderdetail.setId_layanan(idLayanan);
			                    orderdetail.setJumlah(jumlah);
			                    orderdetail.setTotal(total);
			                    ord_detail.save(orderdetail);
			                    reset();
			                    loadTableOrderDetail();
			                    lblHargaShow.setText("Rp. " + total_harga);
			                } catch (NumberFormatException ex) {
			                    JOptionPane.showMessageDialog(null, "Jumlah dan total harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan disimpan");
			        }
				}
			}
		});
		btnSimpan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSimpan.setBounds(220, 336, 85, 34);
		contentPane.add(btnSimpan);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.setBackground(Color.BLUE);
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_ord != null) {
					OrderDetail orderdetail = new OrderDetail();
					orderdetail.setId_order(id_order);
					orderdetail.setId_layanan(lblId_layanan.getText());
					orderdetail.setJumlah(Integer.parseInt(txtJumlah.getText()));
					orderdetail.setTotal(Integer.parseInt(txtTotal.getText()));
					orderdetail.setId_order_detail(id_ord);
					ord_detail.update(orderdetail);
					reset();
					loadTableOrderDetail();
					lblHargaShow.setText("Rp. " + total_harga);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan diedit");
				}
			}
		});
		btnUbah.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUbah.setBounds(317, 336, 85, 34);
		contentPane.add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBackground(Color.RED);
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_ord != null) {
					ord_detail.delete(id_ord);
					reset();
					loadTableOrderDetail();
					lblHargaShow.setText("Rp. " + total_harga);
					
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				}
			}
		});
		btnHapus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHapus.setBounds(413, 336, 85, 34);
		contentPane.add(btnHapus);
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.setBackground(Color.YELLOW);
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnBatal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBatal.setBounds(508, 336, 85, 34);
		contentPane.add(btnBatal);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(222, 383, 554, 170);
		contentPane.add(scrollPane_1);
		
		tableOrderDetail = new JTable();
		tableOrderDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_ord = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 0).toString();
				int selectedRow = tableOrderDetail.getSelectedRow();
				if (selectedRow != -1) {
					String idLayanan = tableOrderDetail.getValueAt(selectedRow, 2).toString();
					for (Service service : ls_srv) {
						if (service.getId().equals(idLayanan)) {
							txtHargaKg.setText(String.valueOf(service.getHarga()));
							break;
						}
					}
				}
				
				txtJumlah.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 3).toString());
				txtTotal.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 4).toString());
				id_order = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 1).toString();
				lblId_layanan.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 2).toString());
				
				
			}
		});
		scrollPane_1.setViewportView(tableOrderDetail);
		
		JButton btnSimpanOrder = new JButton("Simpan");
		btnSimpanOrder.setBackground(Color.GREEN);
		btnSimpanOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order != null) {
					Order order = new Order();
					order.setId_order(id_order);
					order.setNama(txtCostumer.getText());
					order.setTanggal(tgl);
					order.setTanggal_kembali(tgl_kbl);
					order.setStatus(boxStatus.getSelectedItem().toString());
					order.setTotal_harga(total_harga);
					order.setPembayaran(boxPembayaran.getSelectedItem().toString());
					order.setStatus_bayar(boxStatusBayar.getSelectedItem().toString());
					ord_repo.save(order);
					OrderFrame.loadTable();
					OrderDetailFrame.this.dispose();
					resetAll();
					
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan disimpan");
				}
			}
		});
		btnSimpanOrder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSimpanOrder.setBounds(20, 478, 81, 19);
		contentPane.add(btnSimpanOrder);
		
		JButton btnBatalOrder = new JButton("Batal");
		btnBatalOrder.setBackground(Color.YELLOW);
		btnBatalOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBatalOrder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBatalOrder.setBounds(111, 478, 85, 19);
		contentPane.add(btnBatalOrder);
		
		txtCostumer = new JTextField();
		txtCostumer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DialogPelanggan dialog = new DialogPelanggan(OrderDetailFrame.this);
				dialog.setVisible(true);
				dialog.loadTable();
			}
		});
		txtCostumer.setColumns(10);
		txtCostumer.setBounds(20, 104, 170, 19);
		contentPane.add(txtCostumer);
	}
	
}
