package com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.pengguna;

public class PenggunaDTO {
    private Long nisn;
    private String email;
    private String nama;
    private String password;
    private Long kelas_id;

    public Long getNisn() {
        return nisn;
    }

    public void setNisn(Long nisn) {
        this.nisn = nisn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(Long kelas_id) {
        this.kelas_id = kelas_id;
    }
}
