package com.kamilabiyev.ecommerce.service.utils.mapper;

import com.kamilabiyev.ecommerce.domain.entity.Customer;
import com.kamilabiyev.ecommerce.domain.model.dto.customer.CustomerDto;
import com.kamilabiyev.ecommerce.domain.model.dto.customer.CustomerProfileDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFullName(),
                customer.getUser().getUsername(),
                customer.getLocation(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    public CustomerProfileDto toCustomerProfileDto(Customer customer) {
        return new CustomerProfileDto(
                customer.getId(),
                customer.getFullName(),
                customer.getBalance(),
                customer.getUser().getEmail(),
                customer.getUser().getUsername(),
                customer.getLocation(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    public List<CustomerDto> toCustomerDto(List<Customer> categories) {
        return categories.stream().map(this::toCustomerDto).collect(Collectors.toList());
    }
}
