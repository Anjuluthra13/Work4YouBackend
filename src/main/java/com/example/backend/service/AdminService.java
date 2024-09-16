package com.example.backend.service;

import com.example.backend.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    // Hardcoded admin credentials
    private static final String ADMIN_EMAIL = "oak.work4you@gmail.com";
    private static final String ADMIN_PASSWORD = "Admin@w4u";

    @Autowired
    private JwtUtil jwtUtil; // Inject JwtUtil to generate tokens

    public String loginAdmin(String email, String password) {
        // Check if the provided email and password match the hardcoded credentials
        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {
            // Generate JWT token if credentials are valid
            return jwtUtil.generateToken(email);
        }
        return null; // Return null if credentials are invalid
    }
}
