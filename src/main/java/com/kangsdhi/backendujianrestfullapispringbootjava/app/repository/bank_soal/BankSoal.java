package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.bank_soal;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal.Soal;
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

    @Column(name = "nilai_a", columnDefinition = "INT(10) UNSIGNED")
    private String nilai_a;

    @Column(name = "nilai_b", columnDefinition = "INT(10) UNSIGNED")
    private String nilai_b;

    @Column(name = "nilai_c", columnDefinition = "INT(10) UNSIGNED")
    private String nilai_c;

    @Column(name = "nilai_d", columnDefinition = "INT(10) UNSIGNED")
    private String nilai_d;

    @Column(name = "nilai_e", columnDefinition = "INT(10) UNSIGNED")
    private String nilai_e;
}
