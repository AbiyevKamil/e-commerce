package com.kamilabiyev.ecommerce.domain.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS aa", timezone = "Asia/Baku")
    private Timestamp generatedAt;

    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.generatedAt = Timestamp.from(Instant.now());
    }
}
