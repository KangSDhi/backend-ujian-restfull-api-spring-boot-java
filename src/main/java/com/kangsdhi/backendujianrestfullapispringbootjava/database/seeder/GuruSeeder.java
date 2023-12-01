package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.Kelas;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.KelasRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GuruSeeder {
    
    @Autowired
    PenggunaRepository penggunaRepository;
    
    @Autowired
    RolePenggunaRepository rolePenggunaRepository;

    @Autowired
    KelasRepository kelasRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final String GURU = "GURU";
    
    public GuruSeeder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    
    public void createGuru(){
        String nama = "Dhini Aprilia Budiarti";
        String email = "da.budiarti@gmail.com";
        String password = "guru123";

        Pengguna pengguna = penggunaRepository.findPenggunaByEmail(email);
        if (pengguna == null){
            RolePengguna rolePengguna = rolePenggunaRepository.findRolePenggunaByRole(GURU);
            Kelas kelas = kelasRepository.findKelasByKelas("XI GMT 1");
            Pengguna penggunaBaru = new Pengguna();
            penggunaBaru.setNama(nama);
            penggunaBaru.setEmail(email);
            penggunaBaru.setPassword(passwordEncoder.encode(password));
            penggunaBaru.setRolePengguna(rolePengguna);
            penggunaBaru.setKelas(kelas);
            penggunaRepository.save(penggunaBaru);
            System.out.println("Membuat Guru Baru : "+nama+" ✅");
        }
    }
}
