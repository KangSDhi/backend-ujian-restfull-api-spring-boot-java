package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Kelas;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.KelasRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.RolePenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    private final String GURU = "GURU";

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
