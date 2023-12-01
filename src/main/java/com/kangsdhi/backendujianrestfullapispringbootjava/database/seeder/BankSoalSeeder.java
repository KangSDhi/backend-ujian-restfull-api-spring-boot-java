package com.kangsdhi.backendujianrestfullapispringbootjava.database.seeder;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.bank_soal.BankSoal;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.bank_soal.BankSoalRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.kelas.Tingkat;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal.Soal;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.soal.SoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankSoalSeeder {

    @Autowired
    SoalRepository soalRepository;

    @Autowired
    BankSoalRepository bankSoalRepository;

    public void createBankSoal() {
        int countAllBankSoal = bankSoalRepository.findAll().toArray().length;
        System.out.println(countAllBankSoal);
        if (countAllBankSoal == 0){

            Soal soalBahasaInggrisXI = soalRepository.findSoalByNamaAndTingkat("Bahasa Inggris", Tingkat.XI);
//            System.out.println(soalBahasaInggrisXI.getId());
            List<BankSoal> bankSoals = new ArrayList<>();

            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Andi\t:  I heard you passed the TOEFL test. Congratulation.\n" + "Mayumi\t:  _________",
                    null,
                    "I’m sorry . My score is 350",
                    "Thank you, Andi. My score is 570",
                    "Are you kidding?",
                    "Ok. That’s the point",
                    "It’s a good idea",
                    0F,
                    10F,
                    0F,
                    0F,
                    0F
            ));
            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Linda\t:  Hi, Tony. Come in.\n" + "Tony\t:  Linda, you seem very busy in the kitchen.\n" + "Linda\t:  ____________",
                    null,
                    "No, I’m waiting for mom",
                    "No, I’m washing my dolls",
                    "Yes, I’m watering some flowers",
                    "Yes, I’m playing tennis with my niece",
                    "Yes, I’m preparing gado-gado salad for lunch",
                    0F,
                    0F,
                    0F,
                    0F,
                    10F
            ));
            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Iqbal\t:  Could I offer you a glass of avocado juice?\n" + "Eko\t:  _______________",
                    null,
                    "I appreciate that but I can do it myself",
                    "I don’t want to buy some avocado",
                    "I’m in hurry for the first flight",
                    "No, you are wrong",
                    "I’m sorry. I don’t know.",
                    10F,
                    0F,
                    0F,
                    0F,
                    0F
            ));
            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Abdul Qodir\t:  How is the test?\n" + "Azizah\t:  I __________you if I passed the test.",
                    null,
                    "will treat",
                    "will have treated",
                    "would have treated",
                    "would treat",
                    "treat",
                    0F,
                    0F,
                    0F,
                    10F,
                    0F
            ));
            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Fadil\t:  Hi, Novi. How is it going?\n" + "Novi\t:  Not so well.\n" + "Fadil\t:  Why? What’s the matter?\n" + "Novi\t:  My father has been dismissed from his job. This may make him stressed.\n" + "Fadil\t: _______________",
                    null,
                    "Don’t worry. I’m sure he will be fine",
                    "I’m sorry. I hope you get another job soon",
                    "That’s alright.  He will be happy",
                    "No problem. He will enjoy his life",
                    "Come on. You can make it",
                    10F,
                    0F,
                    0F,
                    0F,
                    0F
            ));
            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Anisah\t:  We will hold Tahfidz Inauguration next month. Would you like to come to the program?\n" + "Amirah\t:  _________________",
                    null,
                    "I think you should check first",
                    "Congratulation. That’s a good program",
                    "I’d love to. I will see the performance",
                    "Are you the leader?",
                    "Thank you",
                    0F,
                    0F,
                    10F,
                    0F,
                    0F
            ));
            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Anton\t:  How do you see  our performance?\n" + "Luthfiah\t:  ____________",
                    null,
                    "I’m sorry for coming late",
                    "I’m sorry to hear that",
                    "I think it amazing",
                    "It’s a good idea",
                    "I am not sure",
                    0F,
                    0F,
                    10F,
                    0F,
                    0F
            ));
            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Romania\t: You take a Grab here. Where is your car?\n" + "Armenia\t: _____________",
                    null,
                    "It has repaired in Juanda Motor",
                    "It was repairing in Juanda Motor",
                    "It is being repaired in Juanda Motor",
                    "It has been repairing in Juanda Motor",
                    "It will have been repairing in Juanda Motor",
                    0F,
                    0F,
                    10F,
                    0F,
                    0F
            ));
            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Sea stars, commonly called, “starfish” are not fish. Sea stars live underwater, but that is where their resemblance to fish ends. They do not have gills, scales, or fins. Sea stars live only in saltwater. Sea water, instead of blood, is actually used to pump nutrients through their bodies via a 'water vascular system.'\n" +
                    "Also, sea stars move by using tiny tube feet located on the underside of their bodies. With so many little legs, starfish can reach enormous speeds. Adult sunflower sea stars can move at the astonishing speed of one meter per minute using 15,000 tube feet. Tube feet also help sea stars hold their prey.\n" +
                    "Sea stars are related to sand dollars, sea urchins, and sea cucumbers, all of which are echinoderms, meaning that they have five-point radial symmetry. However, this does not mean that all sea stars have five arms and species with 10, 20, or even 40 arms exist! If one of these arms is lost, a sea star has the amazingly ability to regenerate it.\n"+
                    "What is the purpose of the text?",
                    null,
                    "To tell how sea stars live.",
                    "To describe particular sea stars.",
                    "To convince that sea stars are not fish.",
                    "To compare between sea stars and fish.",
                    "To inform general description of sea stars.",
                    0F,
                    10F,
                    0F,
                    0F,
                    0F
            ));
            bankSoals.add(new BankSoal(
                    soalBahasaInggrisXI,
                    "Sea stars, commonly called, “starfish” are not fish. Sea stars live underwater, but that is where their resemblance to fish ends. They do not have gills, scales, or fins. Sea stars live only in saltwater. Sea water, instead of blood, is actually used to pump nutrients through their bodies via a 'water vascular system.'\n" +
                    "Also, sea stars move by using tiny tube feet located on the underside of their bodies. With so many little legs, starfish can reach enormous speeds. Adult sunflower sea stars can move at the astonishing speed of one meter per minute using 15,000 tube feet. Tube feet also help sea stars hold their prey.\n" +
                    "Sea stars are related to sand dollars, sea urchins, and sea cucumbers, all of which are echinoderms, meaning that they have five-point radial symmetry. However, this does not mean that all sea stars have five arms and species with 10, 20, or even 40 arms exist! If one of these arms is lost, a sea star has the amazingly ability to regenerate it.\n"+
                    "How do we compare paragraphs 2 and 3?",
                    null,
                    "Paragraph 2 is about how sea stars feed but paragraph 3 is about the form of sea stars.",
                    "Paragraph 2 is about how sea stars move but paragraph 3 is about category of sea stars. ",
                    "Paragraph 2 is about the action of sea stars while paragraph 3 is about their regeneration.",
                    "Paragraph 2 is about the movement of sea stars but paragraph 3 is about their relationship.",
                    "Paragraph 2 is about adult sunflower sea stars while paragraph 3 is about the arms of sea stars.",
                    0F,
                    0F,
                    0F,
                    0F,
                    10F
            ));

            for (int i = 0; i < bankSoals.toArray().length; i++) {
                BankSoal bankSoalBaru = new BankSoal();
                bankSoalBaru.setSoal(bankSoals.get(i).getSoal());
                bankSoalBaru.setPertanyaan(bankSoals.get(i).getPertanyaan());
                bankSoalBaru.setGambar_pertanyaan(bankSoals.get(i).getGambar_pertanyaan());
                bankSoalBaru.setPilihan_a(bankSoals.get(i).getPilihan_a());
                bankSoalBaru.setPilihan_b(bankSoals.get(i).getPilihan_b());
                bankSoalBaru.setPilihan_c(bankSoals.get(i).getPilihan_c());
                bankSoalBaru.setPilihan_d(bankSoals.get(i).getPilihan_d());
                bankSoalBaru.setPilihan_e(bankSoals.get(i).getPilihan_e());
                bankSoalBaru.setNilai_a(bankSoals.get(i).getNilai_a());
                bankSoalBaru.setNilai_b(bankSoals.get(i).getNilai_b());
                bankSoalBaru.setNilai_c(bankSoals.get(i).getNilai_c());
                bankSoalBaru.setNilai_d(bankSoals.get(i).getNilai_d());
                bankSoalBaru.setNilai_e(bankSoals.get(i).getNilai_e());

                bankSoalRepository.save(bankSoalBaru);
                System.out.println("Menambahkan Bank Soal Baru!");
            }
        }

    }
}
