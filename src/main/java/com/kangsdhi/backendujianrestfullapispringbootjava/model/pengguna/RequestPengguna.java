package com.kangsdhi.backendujianrestfullapispringbootjava.model.pengguna;

public class RequestPengguna {

    private Integer NISN;
    private String nama;
    private String email;
    private String password;
    private Long kelas_id;

    public RequestPengguna(Integer NISN, String nama, String email, String password, Long role_id, Long kelas_id){
        this.NISN = NISN;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.kelas_id = kelas_id;
    }

    public Integer getNISN() {
        return NISN;
    }
    public String getNama() {
        return nama;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Long getKelas_id() {
        return kelas_id;
    }

}
