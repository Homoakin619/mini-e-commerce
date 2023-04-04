package com.blog.app.security.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenModel, Long> {
    Optional<TokenModel> findByToken(String token);
}
