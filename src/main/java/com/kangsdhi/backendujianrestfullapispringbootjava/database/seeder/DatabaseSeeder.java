package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeeder {

    @Autowired
    PenggunaRepository penggunaRepository;

    @Autowired
    RolePenggunaRepository rolePenggunaRepository;

    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    private void seedPenggunaAdminTable(){

        String nama = "Sigit Boworaharjo";
        String email = "kangadmin@gmail.com";
        String password = "mimin123";
        RolePengguna rolePengguna = new RolePengguna();
        rolePengguna.setId(1L);

        Pengguna pengguna = penggunaRepository.findPenggunaByEmail(email);
        if (pengguna == null){
            Pengguna penggunaBaru = new Pengguna();
            penggunaBaru.setNama(nama);
            penggunaBaru.setEmail(email);
            penggunaBaru.setPassword(passwordEncoder.encode(password));
            penggunaBaru.setRolePengguna(rolePengguna);
            penggunaRepository.save(penggunaBaru);
            System.out.println("Buat Admin Baru : "+nama);
        }

    }

    private void seedPenggunaGuruTable(){
        String nama = "Sigit Boworaharjo";
        String email = "kangguru@gmail.com";
        String password = "mimin123";
        RolePengguna rolePengguna = new RolePengguna();
        rolePengguna.setId(2L);

        Pengguna pengguna = penggunaRepository.findPenggunaByEmail(email);
        if (pengguna == null){
            Pengguna penggunaBaru = new Pengguna();
            penggunaBaru.setNama(nama);
            penggunaBaru.setEmail(email);
            penggunaBaru.setPassword(passwordEncoder.encode(password));
            penggunaBaru.setRolePengguna(rolePengguna);
            penggunaRepository.save(penggunaBaru);
            System.out.println("Buat Guru Baru : "+nama);
        }
    }

    private void seedRoleTable(){
        String[] role = {"ADMIN", "GURU", "SISWA"};
        for (String item: role){
            RolePengguna rolePengguna = rolePenggunaRepository.findRolePenggunaByRole(item);
            if (rolePengguna == null){
                RolePengguna rolePenggunaBaru = new RolePengguna();
                rolePenggunaBaru.setRole(item);
                rolePenggunaRepository.save(rolePenggunaBaru);
                System.out.println("Buat Role Baru "+item);
            }
        }
    }

    @EventListener
    public void seed(ContextRefreshedEvent event){
        seedRoleTable();
        seedPenggunaAdminTable();
        seedPenggunaGuruTable();
    }
}
