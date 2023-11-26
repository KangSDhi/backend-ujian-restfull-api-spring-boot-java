package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.Jurusan;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.JurusanRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.Kelas;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.KelasRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.Tingkat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KelasSeeder {

    @Autowired
    KelasRepository kelasRepository;

    @Autowired
    JurusanRepository jurusanRepository;

    public void createKelas(){
        String[] dataKelas = new String[]{
                "X TKP 1",
                "X TKP 2",
                "XI TKP 1",
                "XI TKP 2",
                "XII TKP 1",
                "XII TKP 2",
                "X DPIB 1",
                "X DPIB 2",
                "XI DPIB 1",
                "XI DPIB 2",
                "XII DPIB 1",
                "XII DPIB 2",
                "X KI 1",
                "XI KI 1",
                "XII KI 1",
                "X GMT 1",
                "X GMT 2",
                "XI GMT 1",
                "XI GMT 2",
                "XII GMT 1",
                "XII GMT 2",
                "X TITL 1",
                "X TITL 2",
                "XI TITL 1",
                "XI TITL 2",
                "XII TITL 1",
                "XII TITL 2",
                "X TKJ 1",
                "X TKJ 2",
                "XI TKJ 1",
                "XI TKJ 2",
                "XII TKJ 1",
                "XII TKJ 2",
                "X MEKA 1",
                "XI MEKA 1",
                "XII MEKA 1",
                "XIII MEKA 1",
                "X TKR 1",
                "X TKR 2",
                "X TKR 3",
                "XI TKR 1",
                "XI TKR 2",
                "XI TKR 3",
                "XII TKR 1",
                "XII TKR 2",
                "XII TKR 3",
                "X TP 1",
                "X TP 2",
                "X TP 3",
                "X TEI 1",
                "X TEI 2",
                "XI TEI 1",
                "XI TEI 2",
                "XII TEI 1",
                "XII TEI 2",
        };

        for (String item: dataKelas){
            Kelas kelas = kelasRepository.findKelasByKelas(item);
            if (kelas == null) {

                Kelas kelasBaru = new Kelas();
                Jurusan jurusan = new Jurusan();

                if (item.contains("X ")){
                    kelasBaru.setTingkat(Tingkat.X);
                } else if (item.contains("XI ")) {
                    kelasBaru.setTingkat(Tingkat.XI);
                } else if (item.contains("XII ")) {
                    kelasBaru.setTingkat(Tingkat.XII);
                } else if (item.contains("XIII ")) {
                    kelasBaru.setTingkat(Tingkat.XIII);
                }

                if (item.contains("TKP")){
                    jurusan = jurusanRepository.findJurusanByNama("Teknik Konstruksi dan Properti");
                } else if (item.contains("DPIB")) {
                    jurusan = jurusanRepository.findJurusanByNama("Desain Pemodelan dan Informasi Bangunan");
                } else if (item.contains("KI")) {
                    jurusan = jurusanRepository.findJurusanByNama("Kimia Industri");
                } else if (item.contains("GMT")) {
                    jurusan = jurusanRepository.findJurusanByNama("Teknik Geomatika");
                } else if (item.contains("TITL")) {
                    jurusan = jurusanRepository.findJurusanByNama("Teknik Installasi Tenaga Listrik");
                } else if (item.contains("TKJ")) {
                    jurusan = jurusanRepository.findJurusanByNama("Teknik Komputer dan Jaringan");
                } else if (item.contains("MEKA")) {
                    jurusan = jurusanRepository.findJurusanByNama("Teknik Mekatronika");
                } else if (item.contains("TKR")) {
                    jurusan = jurusanRepository.findJurusanByNama("Teknik Kendaraan Ringan Otomotif");
                } else if (item.contains("TP")) {
                    jurusan = jurusanRepository.findJurusanByNama("Teknik Pengelasan");
                } else if (item.contains("TEI")) {
                    jurusan = jurusanRepository.findJurusanByNama("Teknik Elektronika Industri");
                }

                kelasBaru.setJurusan(jurusan);
                kelasBaru.setKelas(item);

                kelasRepository.save(kelasBaru);
                System.out.println(kelasRepository);
                System.out.println("Membuat Data Kelas : "+item+" ✅");
            }
        }
    }
}
