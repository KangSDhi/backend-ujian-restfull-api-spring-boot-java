package com.kangsdhi.backendujianrestfullapispringbootjava.app.model;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.enums.Status;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ujian")
public class Ujian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "list_jawaban", columnDefinition = "LONGTEXT")
    private String list_jawaban;

    @Temporal(TemporalType.TIMESTAMP)
    private Date waktu_selesai;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "pengguna_id", referencedColumnName = "id", nullable = false)
    private Pengguna pengguna;

    @OneToOne
    @JoinColumn(name = "soal_id", referencedColumnName = "id", nullable = false)
    private Soal soal;
}
