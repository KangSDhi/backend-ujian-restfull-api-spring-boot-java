package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Soal;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.enums.Tingkat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoalRepository extends JpaRepository<Soal, Long> {
    Soal findSoalByNamaAndTingkat(String nama, Tingkat tingkat);
}
