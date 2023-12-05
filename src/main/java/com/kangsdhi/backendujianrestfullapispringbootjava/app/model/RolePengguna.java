package com.kangsdhi.backendujianrestfullapispringbootjava.app.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "role_pengguna")
public class RolePengguna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RolePengguna [id="+id+", role="+role+"]";
    }
}
