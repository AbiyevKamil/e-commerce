package com.kamilabiyev.ecommerce.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidCredentialsException extends Exception {
    private String message;
}
