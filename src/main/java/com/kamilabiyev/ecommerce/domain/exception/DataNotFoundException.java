package com.kamilabiyev.ecommerce.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DataNotFoundException extends Exception {
    private String message;
}
