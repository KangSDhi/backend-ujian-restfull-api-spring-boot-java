package com.kangsdhi.backendujianrestfullapispringbootjava.app.controller;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Response;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.pengguna.RequestPengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.pengguna.ResponsePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePengguna;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pengguna")
public class PenggunaController {

    private PasswordEncoder passwordEncoder;

    @Autowired
    PenggunaRepository penggunaRepository;

    public PenggunaController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("")
    public ResponseEntity<Response<ResponsePengguna>> createPengguna(@RequestBody @Valid RequestPengguna requestPengguna) {
        try {
            RolePengguna rolePengguna = new RolePengguna();
            rolePengguna.setId(2L);
            Pengguna _pengguna = penggunaRepository.save(new Pengguna(requestPengguna.getNISN(), requestPengguna.getNama(), requestPengguna.getEmail(), passwordEncoder.encode(requestPengguna.getPassword()), rolePengguna, null));

            System.out.println(_pengguna.getRolePengguna().getId());
            Response<ResponsePengguna> response = getPenggunaResponse(_pengguna);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Response<ResponsePengguna> response = new Response<>(500, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static Response<ResponsePengguna> getPenggunaResponse(Pengguna _pengguna) {
        ResponsePengguna responsePengguna = new ResponsePengguna();
        responsePengguna.setNISN(_pengguna.getNISN());
        responsePengguna.setNama(_pengguna.getNama());
        responsePengguna.setEmail(_pengguna.getEmail());
        responsePengguna.setPassword(_pengguna.getPassword());
        responsePengguna.setRole_id(_pengguna.getRolePengguna().getId());
//        responsePengguna.setKelas_id(_pengguna.getKelas_id());
        return new Response<>(201, "Berhasil Membuat Pengguna", responsePengguna);
    }
}
