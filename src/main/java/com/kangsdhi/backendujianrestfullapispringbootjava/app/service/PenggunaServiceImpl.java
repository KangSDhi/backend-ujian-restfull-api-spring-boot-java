package com.kangsdhi.backendujianrestfullapispringbootjava.app.service;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.Pengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.model.RolePengguna;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.PenggunaRepository;
import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.RolePenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PenggunaServiceImpl implements PenggunaService{

    @Autowired
    PenggunaRepository penggunaRepository;

    @Autowired
    RolePenggunaRepository rolePenggunaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pengguna pengguna = penggunaRepository.findPenggunaByEmail(username);
        return new User(pengguna.getEmail(), pengguna.getPassword(), mapRolesToAuthorities(pengguna.getRolePengguna()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<RolePengguna> rolePenggunas){
        return rolePenggunas.stream().map(rolePengguna -> new SimpleGrantedAuthority(rolePengguna.getRole())).collect(Collectors.toList());
    }
}
