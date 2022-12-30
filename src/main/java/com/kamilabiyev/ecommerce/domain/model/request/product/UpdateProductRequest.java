package com.kamilabiyev.ecommerce.domain.model.request.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    private String title;
    private String description;
    private Long stock;
    private Double price;
    private Long categoryId;
    private Set<MultipartFile> files;
}
