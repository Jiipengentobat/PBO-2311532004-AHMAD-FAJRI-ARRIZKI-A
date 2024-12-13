package model;

public class Pelanggan {
    private String id;
    private String nama;
    private String email;
    private String alamat;
    private String hp;

    // Konstruktor tetap protected atau public
    public Pelanggan(String id, String nama, String email, String alamat, String hp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.hp = hp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {  // Menambahkan setter untuk ID
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getHp() {
        return hp;
    }

    // Inner Builder class
    public static class Builder {
        private String id;
        private String nama;
        private String email = "";
        private String alamat;
        private String hp;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setNama(String nama) {
            this.nama = nama;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAlamat(String alamat) {
            this.alamat = alamat;
            return this;
        }

        public Builder setHp(String hp) {
            this.hp = hp;
            return this;
        }

        public Pelanggan build() {
            return new Pelanggan(id, nama, email, alamat, hp); // Membuat objek Pelanggan
        }
    }
}
