package com.ayush.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ayush.authservice.dto.RegisterRequest;
import com.ayush.authservice.dto.LoginRequest;
import com.ayush.authservice.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authservice;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegisterRequest request){

        return authservice.register(request);

    }

    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody LoginRequest request){
        
        return authservice.login(request);

    }
}
