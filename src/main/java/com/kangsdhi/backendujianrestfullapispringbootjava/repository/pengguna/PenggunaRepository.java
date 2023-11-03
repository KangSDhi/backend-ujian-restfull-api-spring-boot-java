package com.kangsdhi.backendujianrestfullapispringbootjava.repository.pengguna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {
    Pengguna findPenggunaById(Long id);
}
