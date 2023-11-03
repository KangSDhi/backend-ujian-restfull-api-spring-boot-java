package com.kangsdhi.backendujianrestfullapispringbootjava.controller;

import com.kangsdhi.backendujianrestfullapispringbootjava.model.Response;
import com.kangsdhi.backendujianrestfullapispringbootjava.model.pengguna.RequestPengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.model.pengguna.ResponsePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.repository.pengguna.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.repository.pengguna.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.repository.role.RolePengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pengguna")
public class PenggunaController {

    @Autowired
    PenggunaRepository penggunaRepository;

    @PostMapping("")
    public ResponseEntity<Response<ResponsePengguna>> createPengguna(@RequestBody RequestPengguna requestPengguna) {
        try {
            RolePengguna rolePengguna = new RolePengguna();
            rolePengguna.setId(2L);
            Pengguna _pengguna = penggunaRepository.save(new Pengguna(requestPengguna.getNISN(), requestPengguna.getNama(), requestPengguna.getEmail(), requestPengguna.getPassword(), rolePengguna, requestPengguna.getKelas_id()));

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
        responsePengguna.setKelas_id(_pengguna.getKelas_id());
        return new Response<>(201, "Berhasil Membuat Pengguna", responsePengguna);
    }
}
