package com.kangsdhi.backendujianrestfullapispringbootjava.app.model.auth;

public class ResponseAuth {
    private String token;

    private String role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
