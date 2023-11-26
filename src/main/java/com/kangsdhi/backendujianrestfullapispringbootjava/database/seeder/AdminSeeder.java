package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminSeeder {

    @Autowired
    PenggunaRepository penggunaRepository;

    @Autowired
    RolePenggunaRepository rolePenggunaRepository;

    private final PasswordEncoder passwordEncoder;

    private final String ADMIN = "ADMIN";

    public AdminSeeder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public void createAdmin(){
        String nama = "Sigit Boworaharjo";
        String email = "kangteknisi@gmail.com";
        String password = "mimin123";

        Pengguna pengguna = penggunaRepository.findPenggunaByEmail(email);
        if (pengguna == null){
            RolePengguna rolePengguna = rolePenggunaRepository.findRolePenggunaByRole(ADMIN);
            Pengguna penggunaBaru = new Pengguna();
            penggunaBaru.setNama(nama);
            penggunaBaru.setEmail(email);
            penggunaBaru.setPassword(passwordEncoder.encode(password));
            penggunaBaru.setRolePengguna(rolePengguna);
            penggunaRepository.save(penggunaBaru);
            System.out.println("Membuat Admin Baru : "+nama+" ✅");
        }
    }
}
