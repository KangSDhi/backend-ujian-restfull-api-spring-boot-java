package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.Tingkat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoalRepository extends JpaRepository<Soal, Long> {
    Soal findSoalByNamaAndTingkat(String nama, Tingkat tingkat);
}
