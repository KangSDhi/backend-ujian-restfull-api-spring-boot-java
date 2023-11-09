package com.kangsdhi.backendujianrestfullapispringbootjava.app.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.util.auth.TokenValidationException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, ObjectMapper mapper){
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> errorDetails = new HashMap<>();

        try {
            String accessToken = jwtUtil.resolveToken(request);
            if (accessToken == null && Objects.equals(request.getRequestURI(), "/api/auth/login/pengguna")){
                filterChain.doFilter(request, response);
                return;
            }

            if (accessToken == null && Objects.equals(request.getRequestURI(), "/api/auth/login/siswa")){
                filterChain.doFilter(request, response);
                return;
            }

            if (accessToken == null && !Objects.equals(request.getRequestURI(), "/api/auth/login/pengguna")){
                throw new TokenValidationException("Token Kosong");
            }

            System.out.println("token : "+accessToken);
            Claims claims = jwtUtil.resolveClaims(request);

            if (claims != null & jwtUtil.validateClaims(claims)){
                String email = claims.getSubject();
                System.out.println("email : "+email);
                Authentication authentication = new UsernamePasswordAuthenticationToken(email,"", new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch(Exception | TokenValidationException e){
            errorDetails.put("error_message", "Authentication Error");
            errorDetails.put("details", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), errorDetails);
        }
        filterChain.doFilter(request, response);
    }
}
