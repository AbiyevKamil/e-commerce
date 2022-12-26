package com.kamilabiyev.ecommerce.domain.security;

import com.kamilabiyev.ecommerce.domain.exception.InvalidTokenException;
import com.kamilabiyev.ecommerce.service.utils.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.info("Processing authentication for '{}'", request.getRequestURL());

        try {
            final var auth = request.getHeader(AUTHORIZATION);
            if (Objects.nonNull(auth) && auth.startsWith(TOKEN_PREFIX)) {
                final var token = auth.substring(7);
                log.info("Token: {}", token);
                if (jwtUtil.isTokenExpired(token))
                    throw new InvalidTokenException("Token is expired.");
                final var username = jwtUtil.getUsernameFromToken(token);
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(username));
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException |
                MalformedJwtException |
                IncorrectClaimException | IllegalArgumentException ex) {
            throw new InvalidTokenException(ex.getMessage());
        }
    }

    private Authentication getAuthentication(String username) {
        final var principal = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(principal,
                null, principal.getAuthorities());
    }
}