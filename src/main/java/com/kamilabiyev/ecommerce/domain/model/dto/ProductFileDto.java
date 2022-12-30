package com.kamilabiyev.ecommerce.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFileDto {
    private Long id;
    private String filePath;
}
