package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JurusanRepository extends JpaRepository<Jurusan, Long> {
    Jurusan findJurusanByNama(String namaJurusan);
}
