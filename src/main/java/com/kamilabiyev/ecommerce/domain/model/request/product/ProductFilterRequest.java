package com.kamilabiyev.ecommerce.domain.model.request.product;


import com.kamilabiyev.ecommerce.domain.model.request.page.BasePagingRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterRequest extends BasePagingRequest {
    private Long priceLowerBound;
    private Long priceHigherBound;
    private Long categoryId;
    private Long sellerId;
}
