package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.RolePengguna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {
    Pengguna findPenggunaById(Long id);
    Pengguna findPenggunaByEmailAndRolePenggunaIsNot(String email, RolePengguna rolePengguna);
    Pengguna findPenggunaByNISNAndRolePengguna(Integer nisn, RolePengguna rolePengguna);
    Pengguna findPenggunaByEmail(String email);
    List<Pengguna> findPenggunasByRolePengguna(RolePengguna rolePengguna);
}
