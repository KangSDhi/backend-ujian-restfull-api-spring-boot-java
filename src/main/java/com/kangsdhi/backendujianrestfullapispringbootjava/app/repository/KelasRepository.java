package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Kelas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KelasRepository extends JpaRepository<Kelas, Long> {
    Kelas findKelasByKelas(String kelas);
    Kelas findKelasById(Long id);
}
