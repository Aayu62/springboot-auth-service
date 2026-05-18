package com.ayush.authservice.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ayush.authservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
}
