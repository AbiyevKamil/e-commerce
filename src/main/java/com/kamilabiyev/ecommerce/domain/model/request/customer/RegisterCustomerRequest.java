package com.kamilabiyev.ecommerce.domain.model.request.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCustomerRequest {
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String location;
}
