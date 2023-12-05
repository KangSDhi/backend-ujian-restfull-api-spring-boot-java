package com.kangsdhi.backendujianrestfullapispringbootjava.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_soal")
public class BankSoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "soal_id", referencedColumnName = "id", nullable = false)
    private Soal soal;

    @Column(name = "pertanyaan", columnDefinition = "LONGTEXT")
    private String pertanyaan;

    @Column(name = "gambar_pertanyaan", columnDefinition = "MEDIUMTEXT")
    private String gambar_pertanyaan;

    @Column(name = "pilihan_a", columnDefinition = "LONGTEXT")
    private String pilihan_a;

    @Column(name = "pilihan_b", columnDefinition = "LONGTEXT")
    private String pilihan_b;

    @Column(name = "pilihan_c", columnDefinition = "LONGTEXT")
    private String pilihan_c;

    @Column(name = "pilihan_d", columnDefinition = "LONGTEXT")
    private String pilihan_d;

    @Column(name = "pilihan_e", columnDefinition = "LONGTEXT")
    private String pilihan_e;

    @Column(name = "nilai_a", columnDefinition = "FLOAT(10) UNSIGNED")
    private Float nilai_a;

    @Column(name = "nilai_b", columnDefinition = "FLOAT(10) UNSIGNED")
    private Float nilai_b;

    @Column(name = "nilai_c", columnDefinition = "FLOAT(10) UNSIGNED")
    private Float nilai_c;

    @Column(name = "nilai_d", columnDefinition = "FLOAT(10) UNSIGNED")
    private Float nilai_d;

    @Column(name = "nilai_e", columnDefinition = "FLOAT(10) UNSIGNED")
    private Float nilai_e;

    public BankSoal(){

    }

    public BankSoal(Soal soal, String pertanyaan, String gambar_pertanyaan, String pilihan_a, String pilihan_b, String pilihan_c, String pilihan_d, String pilihan_e, Float nilai_a, Float nilai_b, Float nilai_c, Float nilai_d, Float nilai_e) {
        this.soal = soal;
        this.pertanyaan = pertanyaan;
        this.gambar_pertanyaan = gambar_pertanyaan;
        this.pilihan_a = pilihan_a;
        this.pilihan_b = pilihan_b;
        this.pilihan_c = pilihan_c;
        this.pilihan_d = pilihan_d;
        this.pilihan_e = pilihan_e;
        this.nilai_a = nilai_a;
        this.nilai_b = nilai_b;
        this.nilai_c = nilai_c;
        this.nilai_d = nilai_d;
        this.nilai_e = nilai_e;
    }

    public Long getId() {
        return id;
    }

    public Soal getSoal() {
        return soal;
    }

    public void setSoal(Soal soal) {
        this.soal = soal;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getGambar_pertanyaan() {
        return gambar_pertanyaan;
    }

    public void setGambar_pertanyaan(String gambar_pertanyaan) {
        this.gambar_pertanyaan = gambar_pertanyaan;
    }

    public String getPilihan_a() {
        return pilihan_a;
    }

    public void setPilihan_a(String pilihan_a) {
        this.pilihan_a = pilihan_a;
    }

    public String getPilihan_b() {
        return pilihan_b;
    }

    public void setPilihan_b(String pilihan_b) {
        this.pilihan_b = pilihan_b;
    }

    public String getPilihan_c() {
        return pilihan_c;
    }

    public void setPilihan_c(String pilihan_c) {
        this.pilihan_c = pilihan_c;
    }

    public String getPilihan_d() {
        return pilihan_d;
    }

    public void setPilihan_d(String pilihan_d) {
        this.pilihan_d = pilihan_d;
    }

    public String getPilihan_e() {
        return pilihan_e;
    }

    public void setPilihan_e(String pilihan_e) {
        this.pilihan_e = pilihan_e;
    }

    public Float getNilai_a() {
        return nilai_a;
    }

    public void setNilai_a(Float nilai_a) {
        this.nilai_a = nilai_a;
    }

    public Float getNilai_b() {
        return nilai_b;
    }

    public void setNilai_b(Float nilai_b) {
        this.nilai_b = nilai_b;
    }

    public Float getNilai_c() {
        return nilai_c;
    }

    public void setNilai_c(Float nilai_c) {
        this.nilai_c = nilai_c;
    }

    public Float getNilai_d() {
        return nilai_d;
    }

    public void setNilai_d(Float nilai_d) {
        this.nilai_d = nilai_d;
    }

    public Float getNilai_e() {
        return nilai_e;
    }

    public void setNilai_e(Float nilai_e) {
        this.nilai_e = nilai_e;
    }
}
