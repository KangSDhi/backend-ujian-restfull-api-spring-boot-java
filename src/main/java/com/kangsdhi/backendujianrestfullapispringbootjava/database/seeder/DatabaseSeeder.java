package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.Jurusan;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.JurusanRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder.JurusanSeeder;

import java.text.ParseException;

@Service
public class DatabaseSeeder {

    @Autowired
    RolePenggunaSeeder rolePenggunaSeeder;

    @Autowired
    JurusanSeeder jurusanSeeder;

    @Autowired
    KelasSeeder kelasSeeder;

    @Autowired
    AdminSeeder adminSeeder;

    @Autowired
    GuruSeeder guruSeeder;

    @Autowired
    SiswaSeeder siswaSeeder;

    @Autowired
    SoalSeeder soalSeeder;

    @Autowired
    BankSoalSeeder bankSoalSeeder;

    @EventListener
    public void seed(ContextRefreshedEvent event) throws ParseException {
        rolePenggunaSeeder.createRole();
        jurusanSeeder.createJurusan();
        kelasSeeder.createKelas();
        adminSeeder.createAdmin();
        guruSeeder.createGuru();
        siswaSeeder.createSiswa();
        soalSeeder.createSoal();
        bankSoalSeeder.createBankSoal();
    }
}
