package com.generation.italy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.italy.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);
    void deleteByToken(String token);
}
