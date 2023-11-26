package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.Jurusan;
import jakarta.persistence.*;

@Entity
@Table(name = "kelas")
public class Kelas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kelas", unique = true, nullable = false)
    private String kelas;

    @Enumerated(EnumType.STRING)
    private Tingkat tingkat;

    @ManyToOne
    @JoinColumn(name = "jurusan_id", referencedColumnName = "id", nullable = false)
    private Jurusan jurusan;

    public Long getId() {
        return id;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Tingkat getTingkat() {
        return tingkat;
    }

    public void setTingkat(Tingkat tingkat) {
        this.tingkat = tingkat;
    }

    public Jurusan getJurusan() {
        return jurusan;
    }

    public void setJurusan(Jurusan jurusan) {
        this.jurusan = jurusan;
    }
}
