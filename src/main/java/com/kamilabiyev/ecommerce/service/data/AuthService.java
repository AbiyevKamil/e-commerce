package com.kamilabiyev.ecommerce.service.data;

import com.kamilabiyev.ecommerce.domain.model.request.auth.LoginRequest;
import com.kamilabiyev.ecommerce.domain.model.request.customer.RegisterCustomerRequest;
import com.kamilabiyev.ecommerce.domain.model.request.seller.RegisterSellerRequest;
import com.kamilabiyev.ecommerce.domain.model.response.AuthResponse;

public interface AuthService {
    AuthResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest);
    AuthResponse registerSeller(RegisterSellerRequest registerSellerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
