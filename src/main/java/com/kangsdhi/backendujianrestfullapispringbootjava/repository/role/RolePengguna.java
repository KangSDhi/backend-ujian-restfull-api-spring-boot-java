package com.kangsdhi.backendujianrestfullapispringbootjava.repository.role;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "role_pengguna")
public class RolePengguna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
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
}
