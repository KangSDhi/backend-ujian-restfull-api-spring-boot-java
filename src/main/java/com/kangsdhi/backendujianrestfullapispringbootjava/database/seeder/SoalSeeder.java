package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.Jurusan;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.jurusan.JurusanRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.Tingkat;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal.Acak;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal.Soal;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal.SoalRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal.Tipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SoalSeeder {

    @Autowired
    SoalRepository soalRepository;

    @Autowired
    JurusanRepository jurusanRepository;

    public void createSoal() throws ParseException {
        int countSoal = soalRepository.findAll().toArray().length;
        if (countSoal == 0){
            String duaJam = "02:00:00";
            DateFormat format = new SimpleDateFormat("hh:mm:ss");
            Date durasiDuaJam = (Date)format.parse(duaJam);

            Jurusan jurusanGeomatika = jurusanRepository.findJurusanByNama("Teknik Geomatika");

            List<Soal> soal = new ArrayList<>();
            soal.add(new Soal("Bahasa Inggris", 50, Acak.ACAK, System.currentTimeMillis(), durasiDuaJam, Tingkat.XI, null, Tipe.PILIHAN_GANDA));
            soal.add(new Soal("Matematika", 25, Acak.ACAK, System.currentTimeMillis(), durasiDuaJam, Tingkat.XI, null, Tipe.PILIHAN_GANDA));
            soal.add(new Soal("Agama", 40, Acak.ACAK, System.currentTimeMillis(), durasiDuaJam, Tingkat.XI, null, Tipe.PILIHAN_GANDA));
            soal.add(new Soal("C3 X Geo", 50, Acak.ACAK, System.currentTimeMillis(), durasiDuaJam, Tingkat.XI, jurusanGeomatika, Tipe.PILIHAN_GANDA));

            for (int i = 0; i < soal.toArray().length; i++) {
                Soal soalBaru = getSoalBaru(soal, i);

                Soal soalResult = soalRepository.save(soalBaru);

                System.out.println("Menambahkan Soal Baru : "+soalResult.getUuid());
            }
        }
    }

    private static Soal getSoalBaru(List<Soal> soal, int i) {
        Soal soalBaru = new Soal();
        soalBaru.setUuid(UUID.randomUUID());
        soalBaru.setNama(soal.get(i).getNama());
        soalBaru.setAcak(soal.get(i).getAcak());
        soalBaru.setButir(soal.get(i).getButir());
        soalBaru.setDurasi(soal.get(i).getDurasi());
        soalBaru.setTipe(soal.get(i).getTipe());
        soalBaru.setTingkat(soal.get(i).getTingkat());
        soalBaru.setWaktu_mulai(soal.get(i).getWaktu_mulai());
        soalBaru.setJurusan(soal.get(i).getJurusan());
        return soalBaru;
    }
}
