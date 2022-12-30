package com.kamilabiyev.ecommerce.domain.model.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kamilabiyev.ecommerce.domain.model.dto.CategoryDto;
import com.kamilabiyev.ecommerce.domain.model.dto.ProductFileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Long stock;
    private Double price;
    private CategoryDto category;
    private Set<ProductFileDto> files;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS aa", timezone = "Asia/Baku")
    private Timestamp createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS aa", timezone = "Asia/Baku")
    private Timestamp updatedAt;
}
