package com.kangsdhi.backendujianrestfullapispringbootjava.app.controller;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.RequestAuthPengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.RequestAuthSiswa;
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
    public ResponseEntity<Object> loginPengguna(@RequestBody @Valid RequestAuthPengguna requestAuth){
        return authService.loginPenggunaNonSiswa(requestAuth);
    }

    @PostMapping("/login/siswa")
    public ResponseEntity<Object> loginSiswa(@RequestBody @Valid RequestAuthSiswa requestAuthSiswa){
        return authService.loginPenggunaSiswa(requestAuthSiswa);
    }
}
