package com.msaproject.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHashService {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordHashService(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String password){
        return passwordEncoder.encode(password);
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword){
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
