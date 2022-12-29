package com.kamilabiyev.ecommerce.domain.model.request.seller;

import lombok.Data;

@Data
public class RegisterSellerRequest {
    private String username;
    private String name;
    private String email;
    private String password;
    private String description;
}
