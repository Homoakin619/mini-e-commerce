package com.blog.app.security.token;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    @Transactional
    public TokenModel confirmToken(String token) {
        TokenModel tokenQuery = tokenRepository.findByToken(token)
                .orElseThrow(
                        () -> new IllegalStateException("Token is not valid"));
        if (tokenQuery.getConfirmedAt() != null) {
            throw new IllegalStateException("Token already validated");
        }

        if (tokenQuery.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token already expired");
        }

        tokenQuery.setConfirmedAt(LocalDateTime.now());

        return tokenQuery;

    }

    public void generateToken(TokenModel token) {
        tokenRepository.save(token);
    }
}
