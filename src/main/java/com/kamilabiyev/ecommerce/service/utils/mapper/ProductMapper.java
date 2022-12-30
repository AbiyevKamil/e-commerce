package com.kamilabiyev.ecommerce.service.utils.mapper;

import com.kamilabiyev.ecommerce.domain.entity.Product;
import com.kamilabiyev.ecommerce.domain.model.dto.ProductFileDto;
import com.kamilabiyev.ecommerce.domain.model.dto.product.ProductDto;
import com.kamilabiyev.ecommerce.domain.model.request.product.CreateProductRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    CategoryMapper categoryMapper;

    public ProductDto toProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                categoryMapper.toCategoryDto(product.getCategory()),
                product.getProductFiles().stream()
                        .map(file -> new ProductFileDto(file.getId(),
                                file.getFilePath())).collect(Collectors.toSet()),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public List<ProductDto> toProductDto(List<Product> categories) {
        return categories.stream().map(this::toProductDto).collect(Collectors.toList());
    }

    public Product toProduct(CreateProductRequest productRequest) {
        return Product.builder()
                .title(productRequest.getTitle())
                .description(productRequest.getDescription())
                .stock(productRequest.getStock())
                .price(productRequest.getPrice())
                .build();
    }
}
