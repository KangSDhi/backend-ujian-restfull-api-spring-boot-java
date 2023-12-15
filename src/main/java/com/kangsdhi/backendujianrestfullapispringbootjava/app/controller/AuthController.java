package com.kangsdhi.backendujianrestfullapispringbootjava.app.controller;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.AuthPenggunaDTO;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.AuthSiswaDTO;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.pengguna.PenggunaDTO;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login/pengguna")
    public ResponseEntity<Object> loginPengguna(@RequestBody @Valid AuthPenggunaDTO authPenggunaDTO){
        return authService.loginPenggunaNonSiswa(authPenggunaDTO);
    }

    @PostMapping("/login/siswa")
    public ResponseEntity<Object> loginSiswa(@RequestBody @Valid AuthSiswaDTO authSiswaDTO){
        return authService.loginPenggunaSiswa(authSiswaDTO);
    }
}
