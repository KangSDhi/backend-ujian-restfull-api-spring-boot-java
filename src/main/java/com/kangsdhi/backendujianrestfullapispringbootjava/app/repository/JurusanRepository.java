package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Jurusan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JurusanRepository extends JpaRepository<Jurusan, Long> {
    Jurusan findJurusanByNama(String namaJurusan);
}
