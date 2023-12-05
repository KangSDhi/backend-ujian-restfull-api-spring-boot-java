package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.RolePengguna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePenggunaRepository extends JpaRepository<RolePengguna, Long> {
    RolePengguna findRolePenggunaByRole(String namaRole);
}
