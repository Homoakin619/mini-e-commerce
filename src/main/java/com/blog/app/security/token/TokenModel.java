package com.blog.app.security.token;

import java.time.LocalDateTime;

import com.blog.app.appuser.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TokenModel {
    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(generator = "token_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @JoinColumn(name = "user_model_id", nullable = false)
    @ManyToOne
    private UserModel userModel;

    public TokenModel(String token, LocalDateTime createdAt, LocalDateTime expiresAt, UserModel userModel) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.userModel = userModel;
    }

}
