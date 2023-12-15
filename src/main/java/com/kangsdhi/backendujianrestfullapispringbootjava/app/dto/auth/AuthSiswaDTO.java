package com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthSiswaDTO {

    @NotNull(message = "NISN Tidak Boleh Kosong!")
    private Integer NISN;

    @NotBlank(message = "Password Tidak Boleh Kosong!")
    @NotNull(message = "Password Tidak Boleh Kosong!")
    private String password;

    public Integer getNISN() {
        return NISN;
    }

    public void setNISN(Integer NISN) {
        this.NISN = NISN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
