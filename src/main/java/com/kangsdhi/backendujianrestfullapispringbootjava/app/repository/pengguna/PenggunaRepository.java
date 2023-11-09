package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePengguna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {
    Pengguna findPenggunaById(Long id);

    Pengguna findPenggunaByEmailAndRolePenggunaIsNot(String email, RolePengguna rolePengguna);
    Pengguna findPenggunaByNISNAndRolePengguna(Integer nisn, RolePengguna rolePengguna);
    Pengguna findPenggunaByEmail(String email);
}
