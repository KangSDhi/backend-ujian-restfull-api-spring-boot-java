package com.kangsdhi.backendujianrestfullapispringbootjava.app.controller;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.config.auth.JwtGeneratorAndValidator;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestAuthController {

    @Autowired
    PenggunaRepository penggunaRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtGeneratorAndValidator jwtGeneratorAndValidator;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PenggunaService penggunaService;

//    @PostMapping("/api/auth/test/create")
//    public ResponseEntity<Object> registerUser(@RequestBody PenggunaDto penggunaDto){
//        Pengguna pengguna = penggunaService.save(penggunaDto);
//        if (pengguna.equals(null))
//            return generateRespose("Not able to save user ", HttpStatus.BAD_REQUEST, penggunaDto);
//        else
//            return generateRespose("User saved successfully : " + pengguna.getId(), HttpStatus.OK, pengguna);
//    }

    public ResponseEntity<Object> generateRespose(String message, HttpStatus st, Object response){
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", st.value());
        map.put("data", response);

        return new ResponseEntity<>(map, st);
    }

//    @GetMapping("/api/auth/test/login")
//    public String login(@RequestBody PenggunaDto penggunaDto) throws Exception {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(penggunaDto.getEmail(), penggunaDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return jwtGeneratorAndValidator.generateToken(authentication);
//    }

//    @GetMapping("/api/admin")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public String welcomeAdmin(){
//        return "Welcome Admin";
//    }
//
//    @GetMapping("/api/user")
//    @PreAuthorize("hasAuthority('SISWA')")
//    public String welcomeUser(){
//        return "Welcome USER";
//    }

}
