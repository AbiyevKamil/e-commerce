package com.kamilabiyev.ecommerce.service.data.impl;

import com.kamilabiyev.ecommerce.domain.constant.BalanceValue;
import com.kamilabiyev.ecommerce.domain.constant.RoleType;
import com.kamilabiyev.ecommerce.domain.model.entity.Customer;
import com.kamilabiyev.ecommerce.domain.model.entity.Seller;
import com.kamilabiyev.ecommerce.domain.model.entity.User;
import com.kamilabiyev.ecommerce.domain.exception.DataExistsException;
import com.kamilabiyev.ecommerce.domain.exception.InvalidCredentialsException;
import com.kamilabiyev.ecommerce.domain.model.request.auth.LoginRequest;
import com.kamilabiyev.ecommerce.domain.model.request.customer.RegisterCustomerRequest;
import com.kamilabiyev.ecommerce.domain.model.request.seller.RegisterSellerRequest;
import com.kamilabiyev.ecommerce.domain.model.response.AuthResponse;
import com.kamilabiyev.ecommerce.service.utils.jwt.JwtUtil;
import com.kamilabiyev.ecommerce.repository.CustomerRepository;
import com.kamilabiyev.ecommerce.repository.RoleRepository;
import com.kamilabiyev.ecommerce.repository.SellerRepository;
import com.kamilabiyev.ecommerce.repository.UserRepository;
import com.kamilabiyev.ecommerce.service.data.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    CustomerRepository customerRepository;
    SellerRepository sellerRepository;
    RoleRepository roleRepository;
    JwtUtil jwtUtil;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    @SneakyThrows
    @Transactional
    @Override
    public AuthResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        var usernameCheck = userRepository.existsByUsername(registerCustomerRequest.getUsername());
        if (usernameCheck) throw new DataExistsException("Username is already taken.");
        var emailCheck = userRepository.existsByEmail(registerCustomerRequest.getEmail());
        if (emailCheck) throw new DataExistsException("Email is already registered.");
        var user = User.builder().email(registerCustomerRequest.getEmail())
                .username(registerCustomerRequest.getUsername())
                .password(passwordEncoder.encode(registerCustomerRequest.getPassword())).build();
        // Todo: Check role section again to make better
        user.addRole(roleRepository.findByName(RoleType.CUSTOMER.name()).get());
        var customer = Customer.builder().fullName(registerCustomerRequest.getFullName())
                .balance(BalanceValue.ZERO.getValue())
                .location(registerCustomerRequest.getLocation())
                .user(user).build();
        var saved = customerRepository.saveAndFlush(customer);
        var accessToken = jwtUtil.generateAccessToken(saved.getUser());
        var refreshToken = jwtUtil.generateRefreshToken(saved.getUser());
        var response = new AuthResponse(accessToken, refreshToken);
        return response;
    }

    @SneakyThrows
    @Transactional
    @Override
    public AuthResponse registerSeller(RegisterSellerRequest registerSellerRequest) {
        var usernameCheck = userRepository.existsByUsername(registerSellerRequest.getUsername());
        if (usernameCheck) throw new DataExistsException("Username is already taken.");
        var emailCheck = userRepository.existsByEmail(registerSellerRequest.getEmail());
        if (emailCheck) throw new DataExistsException("Email is already registered.");
        var user = User.builder().email(registerSellerRequest.getEmail())
                .username(registerSellerRequest.getUsername())
                .password(passwordEncoder.encode(registerSellerRequest.getPassword())).build();
        // Todo: Check role section again to make better
        user.addRole(roleRepository.findByName(RoleType.SELLER.name()).get());
        var seller = Seller.builder().description(registerSellerRequest.getDescription())
                .balance(BalanceValue.ZERO.getValue())
                .name(registerSellerRequest.getName())
                .user(user).build();
        var saved = sellerRepository.saveAndFlush(seller);
        var accessToken = jwtUtil.generateAccessToken(saved.getUser());
        var refreshToken = jwtUtil.generateRefreshToken(saved.getUser());
        var response = new AuthResponse(accessToken, refreshToken);
        return response;
    }

    @SneakyThrows
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticate(loginRequest);
        var userCheck = userRepository.findByUsername(loginRequest.getUsername());
        if (userCheck.isEmpty()) throw new InvalidCredentialsException("Username is not registered.");
        var user = userCheck.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) throw new InvalidCredentialsException("Password is wrong.");
        var accessToken = jwtUtil.generateAccessToken(user);
        var refreshToken = jwtUtil.generateRefreshToken(user);
        var response = new AuthResponse(accessToken, refreshToken);
        return response;
    }

    private void authenticate(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (DisabledException e) {
            throw new DisabledException("User is disabled", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials", e);
        } catch (LockedException e) {
            throw new LockedException("Account is locked", e);
        }
    }
}
