package com.kamilabiyev.ecommerce.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DataExistsException extends Exception {
    private String message;
}
