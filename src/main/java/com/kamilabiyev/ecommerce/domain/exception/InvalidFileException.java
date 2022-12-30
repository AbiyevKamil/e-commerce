package com.kamilabiyev.ecommerce.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidFileException extends Exception {
    private String message;
}
