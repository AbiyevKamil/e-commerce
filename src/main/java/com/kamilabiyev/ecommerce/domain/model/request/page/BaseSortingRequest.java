package com.kamilabiyev.ecommerce.domain.model.request.page;

import lombok.Data;

@Data
public class BaseSortingRequest {
    private String sortField;
    private Boolean isAsc = Boolean.TRUE;
}
