package model;

public class Service {
 String id, jenis, status;
Double Harga, satuan;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getJenis() {
	return jenis;
}
public void setJenis(String jenis) {
	this.jenis = jenis;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Double getHarga() {
	return Harga;
}
public void setHarga(Double harga) {
	Harga = harga;
}
public Double getSatuan() {
	return satuan;
}
public void setSatuan(Double satuan) {
	this.satuan = satuan;
}
}