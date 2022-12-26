package com.kamilabiyev.ecommerce.service.data;

import com.kamilabiyev.ecommerce.domain.request.auth.LoginRequest;
import com.kamilabiyev.ecommerce.domain.request.customer.RegisterCustomerRequest;
import com.kamilabiyev.ecommerce.domain.request.seller.RegisterSellerRequest;
import com.kamilabiyev.ecommerce.domain.response.AuthResponse;

public interface AuthService {
    AuthResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest);
    AuthResponse registerSeller(RegisterSellerRequest registerSellerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
