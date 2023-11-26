package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.Jurusan;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.Tingkat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "soal")
public class Soal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "butir", nullable = false)
    private Integer butir;

    @Enumerated(EnumType.ORDINAL)
    private Acak acak;

    @Column(name = "waktu_mulai", nullable = false)
    private Integer waktu_mulai;

    @Temporal(TemporalType.TIME)
    private Date durasi;

    @Enumerated(EnumType.ORDINAL)
    private Tingkat tingkat;

    @ManyToOne
    @JoinColumn(name = "jurusan_id", referencedColumnName = "id", nullable = true)
    private Jurusan jurusan;

    @Enumerated(EnumType.ORDINAL)
    private Tipe tipe;



}
