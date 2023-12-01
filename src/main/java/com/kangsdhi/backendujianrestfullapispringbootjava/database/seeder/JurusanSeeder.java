package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.Jurusan;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.JurusanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JurusanSeeder {

    @Autowired
    JurusanRepository jurusanRepository;

    public void createJurusan(){
        String[] jurusans = new String[]{
                "Teknik Konstruksi dan Properti",
                "Desain Pemodelan dan Informasi Bangunan",
                "Kimia Industri",
                "Teknik Geomatika",
                "Teknik Installasi Tenaga Listrik",
                "Teknik Komputer dan Jaringan",
                "Teknik Mekatronika",
                "Teknik Kendaraan Ringan Otomotif",
                "Teknik Pengelasan",
                "Teknik Elektronika Industri",
                "Teknik Alchemist"
        };
        for (String item: jurusans){
            Jurusan jurusan = jurusanRepository.findJurusanByNama(item);
            if (jurusan == null) {
                Jurusan jurusanBaru = new Jurusan();
                jurusanBaru.setNama(item);
                jurusanRepository.save(jurusanBaru);
                System.out.println("Membuat Data Jurusan : "+item+" ✅");
            }
        }
    }
}
