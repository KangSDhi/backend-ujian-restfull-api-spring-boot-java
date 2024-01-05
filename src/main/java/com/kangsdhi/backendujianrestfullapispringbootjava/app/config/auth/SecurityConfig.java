package com.kangsdhi.backendujianrestfullapispringbootjava.app.config.auth;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.advice.AdviceAccessDeniedHandler;
//import com.kangsdhi.backendujianrestfullapispringbootjava.app.advice.AdviceExpiredJwtToken;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    PenggunaService penggunaService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(penggunaService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        System.out.println(authenticationConfiguration.toString());
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/guru/**").hasAuthority("GURU")
                        .requestMatchers("/api/siswa/**").hasAuthority("SISWA")
                        .anyRequest().authenticated())
                .exceptionHandling((exception) -> exception.accessDeniedHandler(accessDeniedHandler()))
//                .exceptionHandling((exception) -> exception.accessDeniedHandler(expiredJwtToken()))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authorizationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtFilter authorizationTokenFilterBean() throws Exception {
        return new JwtFilter();
    }

    @Bean
    AdviceAccessDeniedHandler accessDeniedHandler(){
        return new AdviceAccessDeniedHandler();
    }

//    @Bean
//    AdviceExpiredJwtToken expiredJwtToken(){
//        return new AdviceExpiredJwtToken();
//    }
}
