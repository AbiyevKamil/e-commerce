package com.kamilabiyev.ecommerce.controller;

import com.kamilabiyev.ecommerce.domain.model.request.auth.LoginRequest;
import com.kamilabiyev.ecommerce.domain.model.request.customer.RegisterCustomerRequest;
import com.kamilabiyev.ecommerce.domain.model.request.seller.RegisterSellerRequest;
import com.kamilabiyev.ecommerce.service.data.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    @SecurityRequirements
    public ResponseEntity login(LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @PostMapping("/register-customer")
    @SecurityRequirements
    public ResponseEntity registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        return ResponseEntity.ok().body(authService.registerCustomer(registerCustomerRequest));
    }

    @PostMapping("/register-seller")
    @SecurityRequirements
    public ResponseEntity registerSeller(RegisterSellerRequest registerSellerRequest) {
        return ResponseEntity.ok().body(authService.registerSeller(registerSellerRequest));
    }
}
