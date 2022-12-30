package com.kamilabiyev.ecommerce.service.data;

import com.kamilabiyev.ecommerce.domain.model.dto.product.ProductDto;
import com.kamilabiyev.ecommerce.domain.model.request.product.CreateProductRequest;
import com.kamilabiyev.ecommerce.domain.model.request.product.ProductFilterRequest;
import com.kamilabiyev.ecommerce.domain.model.request.product.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    Long create(CreateProductRequest createProductRequest);
    Long update(UpdateProductRequest updateProductRequest);
    List<ProductDto> getFiltered(ProductFilterRequest productFilterRequest);
    ProductDto getById(Long id);
}
