package com.ayush.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayush.authservice.entity.User;
import com.ayush.authservice.exception.EmailAlreadyExistsException;
import com.ayush.authservice.exception.InvalidCredentialsException;
import com.ayush.authservice.dto.RegisterRequest;
import com.ayush.authservice.dto.LoginRequest;
import com.ayush.authservice.dto.LoginResponse;
import com.ayush.authservice.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


    public String register(RegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                "Email already registered!");
        }

        User user = new User(
            request.getName(),
            request.getEmail(),
            passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        return "User Registered Successfully";

    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> 
                        new InvalidCredentialsException("Invalid Email or Password"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(), 
                user.getPassword());

        if(!passwordMatches){
            throw new InvalidCredentialsException("Incorrect Email or Password");
        }

        String token = jwtService.generateToken(user.getEmail());
                
        return new LoginResponse(token);
    }
}
