package com.kangsdhi.backendujianrestfullapispringbootjava.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.AuthPenggunaDTO;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.dto.auth.ResponseAuthDTO;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String JWT_EXPIRED = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiR1VSVSIsInN1YiI6ImRhLmJ1ZGlhcnRpQGdtYWlsLmNvbSIsImlhdCI6MTcwNDI0NDg5NCwiZXhwIjoxNzA0MjQ1MTk0fQ.NJkoI5m5tASC6pIGkaqu4duOEfwL_eYPp5ZusOdKTQA";

    private String JWT_MALFORMED = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiR1VSVSIsInN1YiI6IZXhwIjoxNzA0MjQ1MTk0fQ.NJkoI5mu4duOEfwL_eYPp5ZusOdKTQA";

    private static StringBuilder JWT_GURU = new StringBuilder();

    private static StringBuilder JWT_ADMIN = new StringBuilder();

    @Test
    @Order(1)
    void testLoginGuruOrAdminShouldReturnBadRequest() throws Exception {
        AuthPenggunaDTO authPenggunaDTO = new AuthPenggunaDTO();
        authPenggunaDTO.setEmail("");
        authPenggunaDTO.setPassword("");

        String requestBody = objectMapper.writeValueAsString(authPenggunaDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login/pengguna")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Order(2)
    void testLoginGuruShouldReturnOK() throws Exception {

        AuthPenggunaDTO authPenggunaDTO = new AuthPenggunaDTO();
        authPenggunaDTO.setEmail("da.budiarti@gmail.com");
        authPenggunaDTO.setPassword("guru123");

        String requestBody = objectMapper.writeValueAsString(authPenggunaDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login/pengguna")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String respBody = result.getResponse().getContentAsString();
        ResponseAuthDTO responseAuthDTO = objectMapper.readValue(respBody, ResponseAuthDTO.class);
        JWT_GURU.append(responseAuthDTO.getToken());
    }

    @Test
    @Order(3)
    void testLoginAdminShouldReturnOK() throws Exception {

        AuthPenggunaDTO authPenggunaDTO = new AuthPenggunaDTO();
        authPenggunaDTO.setEmail("kangteknisi@gmail.com");
        authPenggunaDTO.setPassword("mimin123");

        String requestBody = objectMapper.writeValueAsString(authPenggunaDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login/pengguna")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String respBody = result.getResponse().getContentAsString();
        ResponseAuthDTO responseAuthDTO = objectMapper.readValue(respBody, ResponseAuthDTO.class);
        JWT_ADMIN.append(responseAuthDTO.getToken());
    }

    @Test
    @Order(4)
    void testJwtGuruShouldReturnOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/guru")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + JWT_GURU.toString()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(5)
    void testJwtAdminShouldReturnOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + JWT_ADMIN.toString()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(6)
    void testJwtGuruShouldReturnForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/guru")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + JWT_ADMIN.toString()))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(7)
    void testJwtAdminShouldReturnForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + JWT_GURU.toString()))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(8)
    void testJwtExpired() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + JWT_EXPIRED))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(9)
    void testJwtMalformed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + JWT_MALFORMED))
                .andExpect(status().isForbidden())
                .andDo(print());
    }


}