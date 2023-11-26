package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.nilai;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.ujian.Ujian;
import jakarta.persistence.*;

@Entity
@Table(name = "nilai")
public class Nilai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ujian_id", referencedColumnName = "id", nullable = false)
    private Ujian ujian;

    @Column(name = "skor", columnDefinition = "INT(10) UNSIGNED")
    private Integer skor;
}
