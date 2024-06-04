package com.example.dormitorybe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenId;
    @Column(nullable = false)
    private String refreshToken;
    @Column(nullable = false)
    private String memberEmail;

    public RefreshToken(String token, String email) {
        this.refreshToken = token;
        this.memberEmail = email;
    }

    public RefreshToken updateToken(String token) {
        this.refreshToken = token;
        return this;
    }
}
