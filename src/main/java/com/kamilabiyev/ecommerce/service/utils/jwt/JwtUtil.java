package com.kamilabiyev.ecommerce.service.utils.jwt;

import com.kamilabiyev.ecommerce.domain.model.entity.User;
import com.kamilabiyev.ecommerce.domain.property.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties tokenProperties;
    private final static String ROLE_KEY = "roles";

    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ROLE_KEY, user.getAuthorities());
        Calendar calendar = Calendar.getInstance();
        Date createdDate = calendar.getTime();
        calendar.add(Calendar.DATE, tokenProperties.getExpirationTime());
        Date expirationDate = calendar.getTime();

        return Jwts.builder()
                .setClaims(claims)
                .setId(user.getUserId().toString())
                .setSubject(user.getUsername())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecretKey())
                .compact();
    }

    public String generateRefreshToken(User user) {
        Calendar calendar = Calendar.getInstance();
        Date createdDate = calendar.getTime();
        calendar.add(Calendar.DATE, tokenProperties.getExpirationTime() + 3);
        Date expirationDate = calendar.getTime();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setId(user.getUserId().toString())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimForToken(token, Claims::getSubject);
    }

    public String getIdFromToken(String token) {
        return getClaimForToken(token, Claims::getId);
    }

    public boolean isTokenExpired(String token) {
        var expirationDate = getClaimForToken(token, Claims::getExpiration);
        return !expirationDate.after(new Date());
    }

    private <T> T getClaimForToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsForToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsForToken(String token) {
        return Jwts.parser().setSigningKey(tokenProperties.getSecretKey())
                .parseClaimsJws(token).getBody();
    }
}

