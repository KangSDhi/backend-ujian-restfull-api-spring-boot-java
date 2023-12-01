package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.Jurusan;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.Tingkat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "soal")
public class Soal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "butir", nullable = false)
    private Integer butir;

    @Enumerated(EnumType.ORDINAL)
    private Acak acak;

    @Column(name = "waktu_mulai", nullable = false)
    private Long waktu_mulai;

    @Temporal(TemporalType.TIME)
    private Date durasi;

    @Enumerated(EnumType.STRING)
    private Tingkat tingkat;

    @ManyToOne
    @JoinColumn(name = "jurusan_id", referencedColumnName = "id", nullable = true)
    private Jurusan jurusan;

    @Enumerated(EnumType.STRING)
    private Tipe tipe;

    public Soal(){

    }

    public Soal(String nama, Integer butir, Acak acak, Long waktu_mulai, Date durasi, Tingkat tingkat, Jurusan jurusan, Tipe tipe) {
        this.nama = nama;
        this.butir = butir;
        this.acak = acak;
        this.waktu_mulai = waktu_mulai;
        this.durasi = durasi;
        this.tingkat = tingkat;
        this.jurusan = jurusan;
        this.tipe = tipe;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getButir() {
        return butir;
    }

    public void setButir(Integer butir) {
        this.butir = butir;
    }

    public Acak getAcak() {
        return acak;
    }

    public void setAcak(Acak acak) {
        this.acak = acak;
    }

    public Long getWaktu_mulai() {
        return waktu_mulai;
    }

    public void setWaktu_mulai(Long waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public Date getDurasi() {
        return durasi;
    }

    public void setDurasi(Date durasi) {
        this.durasi = durasi;
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

    public Tipe getTipe() {
        return tipe;
    }

    public void setTipe(Tipe tipe) {
        this.tipe = tipe;
    }

    @Override
    public String toString() {
        return "Soal{" +
                ", butir=" + butir +
                ", acak=" + acak +
                ", waktu_mulai=" + waktu_mulai +
                ", durasi=" + durasi +
                ", tingkat=" + tingkat +
                ", jurusan=" + jurusan +
                ", tipe=" + tipe +
                '}';
    }
}
