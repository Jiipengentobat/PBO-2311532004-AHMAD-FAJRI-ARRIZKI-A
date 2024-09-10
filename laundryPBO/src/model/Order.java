package model;

public class Order {
String id, idCustomer, idService, tanggalOrder, tanggalSelesai, statusBayar, statusPesanan, idUser;
Double TotalHarga;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getIdCustomer() {
	return idCustomer;
}
public void setIdCustomer(String idCustomer) {
	this.idCustomer = idCustomer;
}
public String getIdService() {
	return idService;
}
public void setIdService(String idService) {
	this.idService = idService;
}
public String getTanggalOrder() {
	return tanggalOrder;
}
public void setTanggalOrder(String tanggalOrder) {
	this.tanggalOrder = tanggalOrder;
}
public String getTanggalSelesai() {
	return tanggalSelesai;
}
public void setTanggalSelesai(String tanggalSelesai) {
	this.tanggalSelesai = tanggalSelesai;
}
public String getStatusBayar() {
	return statusBayar;
}
public void setStatusBayar(String statusBayar) {
	this.statusBayar = statusBayar;
}
public String getStatusPesanan() {
	return statusPesanan;
}
public void setStatusPesanan(String statusPesanan) {
	this.statusPesanan = statusPesanan;
}
public String getIdUser() {
	return idUser;
}
public void setIdUser(String idUser) {
	this.idUser = idUser;
}
public Double getTotalHarga() {
	return TotalHarga;
}
public void setTotalHarga(Double totalHarga) {
	TotalHarga = totalHarga;
}
}