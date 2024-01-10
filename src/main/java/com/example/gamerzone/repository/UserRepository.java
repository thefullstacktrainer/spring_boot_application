package com.example.gamerzone.repository;

// com.example.gamerzone.repository.UserRepository

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamerzone.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
