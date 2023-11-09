package com.kangsdhi.backendujianrestfullapispringbootjava.app.controller;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.config.auth.JwtUtil;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Response;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.admin.RequestRegisterAdmin;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.admin.ResponseRegisterAdmin;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.auth.RequestAuthPengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.auth.RequestAuthSiswa;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.auth.ResponseAuth;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.error.ErrorResponse;
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
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final long ADMIN_ID = 1;
    private final long SISWA_ID = 3;

    @Autowired
    PenggunaRepository penggunaRepository;

    public AuthController(JwtUtil jwtUtil, PasswordEncoder passwordEncoder){
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login/pengguna")
    public ResponseEntity<Object> loginPengguna(@RequestBody @Valid RequestAuthPengguna requestAuth){
        try {
            RolePengguna rolePengguna = new RolePengguna();
            rolePengguna.setId(SISWA_ID);
            Pengguna pengguna = penggunaRepository.findPenggunaByEmailAndRolePenggunaIsNot(requestAuth.getEmail(), rolePengguna);
            if (pengguna == null){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Pengguna");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            boolean isPasswordMatches = passwordEncoder.matches(requestAuth.getPassword(), pengguna.getPassword());
            if (!isPasswordMatches){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Pengguna");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            return getLoginSuccessResponse(pengguna);
        } catch (Exception e){
            ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/login/siswa")
    public ResponseEntity<Object> loginSiswa(@RequestBody RequestAuthSiswa requestAuthSiswa){
        try {
            RolePengguna rolePengguna = new RolePengguna();
            rolePengguna.setId(SISWA_ID);
            Pengguna pengguna = penggunaRepository.findPenggunaByNISNAndRolePengguna(requestAuthSiswa.getNISN(), rolePengguna);
            if (pengguna == null){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Siswa");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            boolean isPasswordMatches = passwordEncoder.matches(requestAuthSiswa.getPassword(), pengguna.getPassword());
            if (!isPasswordMatches){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Siswa");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            return getLoginSuccessResponse(pengguna);
        }catch (Exception e){
            ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Object> getLoginSuccessResponse(Pengguna pengguna) {
        String token = jwtUtil.createToken(pengguna);
        ResponseAuth responseAuth = new ResponseAuth();
        responseAuth.setToken(token);
        responseAuth.setRole(pengguna.getRolePengguna().getRole());
        Response<ResponseAuth> response = new Response<>(HttpStatus.OK.value(), "Login Sukses", responseAuth);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<Object> registerAdmin(@RequestBody RequestRegisterAdmin requestAdmin){
        try {
            RolePengguna rolePengguna = new RolePengguna();
            rolePengguna.setId(ADMIN_ID);
            Pengguna _pengguna = penggunaRepository.save(new Pengguna(null, requestAdmin.getNama(), requestAdmin.getEmail(), passwordEncoder.encode(requestAdmin.getPassword()), rolePengguna, null));
            Response<ResponseRegisterAdmin> response = getRegisterAdminResponse(_pengguna);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){
            ErrorResponse<Object> error_response = new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return new ResponseEntity<>(error_response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Response<ResponseRegisterAdmin> getRegisterAdminResponse(Pengguna pengguna) {
        ResponseRegisterAdmin responseAdmin = new ResponseRegisterAdmin();
        responseAdmin.setNama(pengguna.getNama());
        responseAdmin.setEmail(pengguna.getEmail());
        return new Response<>(HttpStatus.CREATED.value(), "Berhasil Membuat Admin", responseAdmin);
    }

    private static ErrorResponse<Object> getErrorResponse(Integer httpCode, String errorMessage){
        return new ErrorResponse<>(httpCode, errorMessage, null);
    }


}
