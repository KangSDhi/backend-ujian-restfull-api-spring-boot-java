package com.kangsdhi.backendujianrestfullapispringbootjava.app.model.auth;

public class RequestAuthSiswa {

    private Integer NISN;

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
