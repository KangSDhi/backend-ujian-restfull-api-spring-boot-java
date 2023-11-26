package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan;

import jakarta.persistence.*;

@Entity
@Table(name = "jurusan")
public class Jurusan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", unique = true)
    private String nama;

    public Jurusan(){

    }

    public Jurusan(Long id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public Long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
