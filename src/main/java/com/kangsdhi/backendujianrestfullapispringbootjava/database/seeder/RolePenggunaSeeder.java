package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePenggunaSeeder {

    @Autowired
    RolePenggunaRepository rolePenggunaRepository;

    public void createRole(){
        String[] roles = new String[]{
                "ADMIN",
                "GURU",
                "SISWA"
        };

        for (String item: roles){
            RolePengguna rolePengguna = rolePenggunaRepository.findRolePenggunaByRole(item);
            if (rolePengguna == null) {
                RolePengguna rolePenggunaBaru = new RolePengguna();
                rolePenggunaBaru.setRole(item);
                rolePenggunaRepository.save(rolePenggunaBaru);
                System.out.println("Membuat Data Role : "+item+" ✅");
            }
        }
    }

}
