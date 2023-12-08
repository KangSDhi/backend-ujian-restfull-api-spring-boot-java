package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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
