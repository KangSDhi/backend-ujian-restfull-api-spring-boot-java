package com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.pengguna;

import jakarta.validation.constraints.*;

public class RequestPengguna {

    @NotNull(message = "NISN Tidak Boleh Kosong!")
    private Long NISN;
    @NotNull(message = "Nama Tidak Boleh Kosong!")
    @NotBlank(message = "Nama Tidak Boleh Kosong!")
    private String nama;
    @NotNull(message = "Email Tidak Boleh Kosong!")
    @NotBlank(message = "Email Tidak Boleh Kosong!")
    @Email(message = "Bukan Format Email!")
    private String email;
    @NotNull(message = "Password Tidak Boleh Kosong!")
    @NotBlank(message = "Password Tidak Boleh Kosong!")
//    @Min(value = 8, message = "Password Minimal 8 Karakter!")
    private String password;
    private Long kelas_id;

    public RequestPengguna(Long NISN, String nama, String email, String password, Long role_id, Long kelas_id){
        this.NISN = NISN;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.kelas_id = kelas_id;
    }

    public Long getNISN() {
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
