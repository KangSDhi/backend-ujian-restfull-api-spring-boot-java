package com.kangsdhi.backendujianrestfullapispringbootjava.app.controller;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Response;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.admin.RequestRegisterAdmin;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.admin.ResponseRegisterAdmin;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.error.ErrorResponse;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final PasswordEncoder passwordEncoder;

    private final long ADMIN_ID = 1;

    @Autowired
    PenggunaRepository penggunaRepository;

    public AdminController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createAdmin(@RequestBody RequestRegisterAdmin registerAdmin){
        try {
            RolePengguna rolePengguna = new RolePengguna();
            rolePengguna.setId(ADMIN_ID);
            Pengguna checkPengguna = penggunaRepository.findPenggunaByEmail(registerAdmin.getEmail());
            if (checkPengguna != null){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email Sudah Terdaftar!");
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Pengguna dataAdmin = new Pengguna();
            dataAdmin.setNama(registerAdmin.getNama());
            dataAdmin.setEmail(registerAdmin.getEmail());
            dataAdmin.setPassword(passwordEncoder.encode(registerAdmin.getPassword()));
            dataAdmin.setRolePengguna(rolePengguna);

            Pengguna createAdmin = penggunaRepository.save(dataAdmin);

            ResponseRegisterAdmin responseRegisterAdmin = new ResponseRegisterAdmin();
            responseRegisterAdmin.setNama(createAdmin.getNama());
            responseRegisterAdmin.setEmail(createAdmin.getEmail());

            Response<ResponseRegisterAdmin> response = new Response<>(HttpStatus.CREATED.value(), "Berhasil Membuat Admin Baru", responseRegisterAdmin);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ErrorResponse<Object> getErrorResponse(int httpCode, String errorMessage){
        return new ErrorResponse<>(httpCode, errorMessage, null);
    }
}
