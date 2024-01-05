package com.kangsdhi.backendujianrestfullapispringbootjava.app.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.service.PenggunaService;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.util.auth.TokenValidationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    PenggunaService penggunaService;

    @Autowired
    JwtGeneratorAndValidator jwtGeneratorAndValidator;

    @Autowired
    ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String authorizationHeader = request.getHeader("Authorization");

            String token = null;
            String username = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                username = jwtGeneratorAndValidator.extractUsername(token);
            }

            if (token == null && !request.getRequestURI().contains("/api/auth/login")){
                throw new TokenValidationException("Token Kosong!");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = penggunaService.loadUserByUsername(username);


                if (jwtGeneratorAndValidator.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = jwtGeneratorAndValidator.getAuthenticationToken(token, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }catch (Exception | TokenValidationException exception){
            handleException(response,  exception);
        }

        filterChain.doFilter(request, response);
    }

    private void handleException(HttpServletResponse response, Throwable exception) throws IOException {
        Map<String, Object> errorDetails = new HashMap<>();

        if (exception instanceof TokenValidationException){
            logger.error("Token Validation Exception");
            logger.error(((TokenValidationException) exception).getMessage());
            errorDetails.put("code", HttpStatus.FORBIDDEN.value());
            errorDetails.put("message", ((TokenValidationException) exception).getMessage());
        }

        if (exception instanceof MalformedJwtException){
            logger.error("Malformed Jwt Exception");
            logger.error(((MalformedJwtException) exception).getMessage());
            logger.error("Format Token Salah!");
            errorDetails.put("code", HttpStatus.FORBIDDEN.value());
            errorDetails.put("message", "Format Token Salah!");
        }

        if (exception instanceof ExpiredJwtException){
            logger.error("Expired Jwt Exception");
            logger.error(((ExpiredJwtException) exception).getMessage());
            logger.error("Token Kadaluarsa!");
            errorDetails.put("code", HttpStatus.FORBIDDEN.value());
            errorDetails.put("message", "Token Kadaluarsa!");
        }

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        mapper.writeValue(response.getWriter(), errorDetails);
    }
}
