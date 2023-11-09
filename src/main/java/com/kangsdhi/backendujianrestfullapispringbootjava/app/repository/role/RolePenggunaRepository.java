package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePenggunaRepository extends JpaRepository<RolePengguna, Long> {
    RolePengguna findRolePenggunaByRole(String namaRole);
}
