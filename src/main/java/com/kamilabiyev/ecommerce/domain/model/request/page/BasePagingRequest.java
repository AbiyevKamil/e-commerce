package com.kamilabiyev.ecommerce.domain.model.request.page;


import javax.validation.constraints.Min;

public class BasePagingRequest extends BaseSortingRequest {
    @Min(0)
    private Integer pageNumber = 0;
    @Min(1)
    private Integer pageSize = 30;
}
