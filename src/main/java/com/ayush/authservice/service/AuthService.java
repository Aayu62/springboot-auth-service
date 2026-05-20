package com.ayush.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayush.authservice.entity.User;
import com.ayush.authservice.dto.RegisterRequest;
import com.ayush.authservice.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    
    public String register(RegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered!";
        }

        User user = new User(
            request.getName(),
            request.getEmail(),
            request.getPassword()
        );

        userRepository.save(user);

        return "User Registered Successfully";

    }
}
