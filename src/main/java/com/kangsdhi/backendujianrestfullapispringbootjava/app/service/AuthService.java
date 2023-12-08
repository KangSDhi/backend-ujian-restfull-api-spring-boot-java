package com.kangsdhi.backendujianrestfullapispringbootjava.app.service;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.config.auth.JwtUtil;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.Response;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.RequestAuthPengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.RequestAuthSiswa;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.ResponseAuth;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.error.ErrorResponse;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final long SISWA_ID = 3;

    @Autowired
    PenggunaRepository penggunaRepository;

    public AuthService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder){
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Object> loginPenggunaNonSiswa(RequestAuthPengguna requestAuthPengguna){
        try {
            RolePengguna rolePengguna = new RolePengguna();
            rolePengguna.setId(SISWA_ID);
            Pengguna pengguna = penggunaRepository.findPenggunaByEmailAndRolePenggunaIsNot(requestAuthPengguna.getEmail(), rolePengguna);
            if (pengguna == null){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Pengguna");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            boolean isPasswordMatches = passwordEncoder.matches(requestAuthPengguna.getPassword(), pengguna.getPassword());
            if (!isPasswordMatches){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Pengguna");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            return getLoginSuccessResponse(pengguna);
        }catch (Exception e){
            ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> loginPenggunaSiswa(RequestAuthSiswa requestAuthSiswa){
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

        } catch (Exception e){
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

    private static ErrorResponse<Object> getErrorResponse(Integer httpCode, String errorMessage){
        return new ErrorResponse<>(httpCode, errorMessage, null);
    }
}
