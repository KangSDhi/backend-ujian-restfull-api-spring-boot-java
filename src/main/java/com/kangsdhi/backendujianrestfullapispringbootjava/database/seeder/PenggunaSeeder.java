package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PenggunaSeeder {

    private final PasswordEncoder passwordEncoder;

    public PenggunaSeeder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


}
