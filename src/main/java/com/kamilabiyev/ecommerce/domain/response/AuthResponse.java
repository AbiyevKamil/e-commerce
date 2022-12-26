package com.kamilabiyev.ecommerce.domain.response;

import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private Timestamp generatedAt;

    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.generatedAt = Timestamp.from(Instant.now());
    }
}
