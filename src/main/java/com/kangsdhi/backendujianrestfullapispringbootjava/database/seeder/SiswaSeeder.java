package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Kelas;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.KelasRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.RolePenggunaRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiswaSeeder {

    @Autowired
    PenggunaRepository penggunaRepository;

    @Autowired
    RolePenggunaRepository rolePenggunaRepository;

    @Autowired
    KelasRepository kelasRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final String SISWA = "SISWA";

    public void createSiswa(){

        RolePengguna rolePengguna = rolePenggunaRepository.findRolePenggunaByRole(SISWA);
        List<Pengguna> penggunas = penggunaRepository.findPenggunasByRolePengguna(rolePengguna);
        int countSiswa = penggunas.toArray().length;

        int size = 5000;

        if (countSiswa == 0){

            for (var i = 0; i <= size - 1; i++){
                Faker faker = new Faker();

                int random1_10000 = (int)(Math.random()*10000+1);
                int random1_1000 = (int)(Math.random()*1000+1);
                int random1_550 = (int)(Math.random()*550+1);
                int random1_55 = (int)(Math.random()*55+1);

                String NISN = random1_550 +String.valueOf(random1_1000)+ random1_10000 + random1_1000;
                String nama = faker.name().fullName();
                String email = faker.name().username()+ random1_1000 + "@gmail.com";
                String password = "siswa123";

                Kelas kelas = kelasRepository.findKelasById((long) random1_55);

                Pengguna penggunaBaru = new Pengguna();
                penggunaBaru.setNISN(Long.parseLong(NISN));
                penggunaBaru.setNama(nama);
                penggunaBaru.setEmail(email);
                penggunaBaru.setPassword(passwordEncoder.encode(password));
                penggunaBaru.setRolePengguna(rolePengguna);
                penggunaBaru.setKelas(kelas);

                penggunaRepository.save(penggunaBaru);

                System.out.println("Membuat Siswa Baru : "+nama+" ✅");
            }
        }
    }
}
