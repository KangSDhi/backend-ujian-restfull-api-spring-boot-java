package com.kangsdhi.backendujianrestfullapispringbootjava.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

@Repository
public interface PenggunaService extends UserDetailsService {
}