package com.kamilabiyev.ecommerce.service.data;

import com.kamilabiyev.ecommerce.domain.model.dto.customer.CustomerDto;
import com.kamilabiyev.ecommerce.domain.model.dto.customer.CustomerProfileDto;
import com.kamilabiyev.ecommerce.domain.model.request.customer.UpdateCustomerRequest;

public interface CustomerService {
    void update(UpdateCustomerRequest updateCustomerRequest);
    void delete();

    CustomerDto getById(Long id);

    CustomerProfileDto getProfile();
}
