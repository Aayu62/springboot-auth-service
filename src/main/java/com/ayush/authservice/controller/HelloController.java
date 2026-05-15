package com.ayush.authservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {

    @GetMapping("/Hello")
    public String sayHello(){
        return "Hello from java Spring Boot!";
    }
}
