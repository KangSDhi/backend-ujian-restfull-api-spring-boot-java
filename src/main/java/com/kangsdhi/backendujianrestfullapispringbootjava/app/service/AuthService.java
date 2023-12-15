package com.kangsdhi.backendujianrestfullapispringbootjava.app.service;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.config.auth.JwtGeneratorAndValidator;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.Response;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.AuthPenggunaDTO;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.AuthSiswaDTO;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.error.ErrorResponse;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final long SISWA_ID = 3;

    @Autowired
    PenggunaRepository penggunaRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtGeneratorAndValidator jwtGeneratorAndValidator;

    public ResponseEntity<Object> loginPenggunaNonSiswa(AuthPenggunaDTO authPenggunaDTO){
        try{
            RolePengguna rolePengguna = new RolePengguna();
            rolePengguna.setId(SISWA_ID);
            Pengguna pengguna = penggunaRepository.findPenggunaByEmailAndRolePenggunaIsNot(authPenggunaDTO.getEmail(), rolePengguna);
            if (pengguna == null){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Pengguna");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            boolean isPasswordMatches = passwordEncoder.matches(authPenggunaDTO.getPassword(), pengguna.getPassword());
            if (!isPasswordMatches){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Pengguna");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authPenggunaDTO.getEmail(), authPenggunaDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGeneratorAndValidator.generateToken(authentication);

            Map<String, Object> responseAuth = new HashMap<>();

            responseAuth.put("token", token);
            responseAuth.put("role", pengguna.getRolePengguna().iterator().next().getRole());

            return new ResponseEntity<>(responseAuth, HttpStatus.OK);
        }catch (Exception e){
            ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> loginPenggunaSiswa(AuthSiswaDTO authSiswaDTO){
        try {
            RolePengguna rolePengguna = new RolePengguna();
            rolePengguna.setId(SISWA_ID);
            Pengguna pengguna = penggunaRepository.findPenggunaByNISNAndRolePengguna(authSiswaDTO.getNISN(), rolePengguna);
            if (pengguna == null){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Siswa");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            boolean isPasswordMatches = passwordEncoder.matches(authSiswaDTO.getPassword(), pengguna.getPassword());
            if (!isPasswordMatches){
                ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.NOT_FOUND.value(), "Anda Bukan Siswa");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(pengguna.getEmail(), authSiswaDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(credentials);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGeneratorAndValidator.generateToken(authentication);

            Map<String, Object> responseAuth = new HashMap<>();

            responseAuth.put("token", token);
            responseAuth.put("role", pengguna.getRolePengguna().iterator().next().getRole());

            return new ResponseEntity<>(responseAuth, HttpStatus.OK);
        } catch (Exception e){
            ErrorResponse<Object> errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static ErrorResponse<Object> getErrorResponse(Integer httpCode, String errorMessage){
        return new ErrorResponse<>(httpCode, errorMessage, null);
    }
}
