package com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestAuthPengguna {

    @NotNull(message = "Email Tidak Boleh Kosong!")
    @NotBlank(message = "Email Tidak Boleh Kosong!")
    @Email(message = "Format Bukan Email!")
    private String email;

    @NotBlank(message = "Password Tidak Boleh Kosong!")
    @NotNull(message = "Password Tidak Boleh Kosong!")
    private String password;

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
}
