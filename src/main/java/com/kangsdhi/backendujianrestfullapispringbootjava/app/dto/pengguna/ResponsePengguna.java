package com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.pengguna;

public class ResponsePengguna {
    private Long NISN;
    private String nama;
    private String email;
    private String password;
    private Long role_id;
    private Long kelas_id;

    public Long getNISN() {
        return NISN;
    }

    public void setNISN(Long NISN) {
        this.NISN = NISN;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(Long kelas_id) {
        this.kelas_id = kelas_id;
    }
}
