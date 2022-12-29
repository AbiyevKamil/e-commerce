package com.kamilabiyev.ecommerce.service.data.impl;

import com.kamilabiyev.ecommerce.domain.exception.DataNotFoundException;
import com.kamilabiyev.ecommerce.domain.model.dto.customer.CustomerDto;
import com.kamilabiyev.ecommerce.domain.model.dto.customer.CustomerProfileDto;
import com.kamilabiyev.ecommerce.domain.model.request.customer.UpdateCustomerRequest;
import com.kamilabiyev.ecommerce.repository.CustomerRepository;
import com.kamilabiyev.ecommerce.repository.UserRepository;
import com.kamilabiyev.ecommerce.service.data.CustomerService;
import com.kamilabiyev.ecommerce.service.utils.mapper.CustomerMapper;
import com.kamilabiyev.ecommerce.service.utils.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CustomerMapper customerMapper;

    @SneakyThrows
    @Transactional
    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest) {
        var userDetails = SecurityUtil.getPrincipal().get();
        var userCheck = userRepository
                .findByUsernameAndIsDeleted(userDetails.getUsername(), false);
        if (userCheck.isEmpty()) throw new DataNotFoundException("User does not exist.");
        var user = userCheck.get();
        var customerCheck = customerRepository.findByUserIdAndIsDeleted(user.getUserId(), false);
        if (customerCheck.isEmpty()) throw new DataNotFoundException("Customer does not exist.");
        var customer = customerCheck.get();
        customer.setFullName(updateCustomerRequest.getFullName());
        customer.setLocation(updateCustomerRequest.getLocation());
        customerRepository.saveAndFlush(customer);
    }

    @SneakyThrows
    @Override
    @Transactional
    public void delete() {
        var userDetails = SecurityUtil.getPrincipal().get();
        var userCheck = userRepository
                .findByUsernameAndIsDeleted(userDetails.getUsername(), false);
        if (userCheck.isEmpty()) throw new DataNotFoundException("User does not exist.");
        var user = userCheck.get();
        user.setIsDeleted(true);
        userRepository.save(user);
        var customerCheck = customerRepository.findByUserIdAndIsDeleted(user.getUserId(), false);
        if (customerCheck.isEmpty()) throw new DataNotFoundException("Customer does not exist.");
        var customer = customerCheck.get();
        customer.setIsDeleted(true);
        customerRepository.saveAndFlush(customer);
    }

    @SneakyThrows
    @Override
    public CustomerDto getById(Long id) {
        var customerCheck = customerRepository.findByUserIdAndIsDeleted(id, false);
        if (customerCheck.isEmpty()) throw new DataNotFoundException("Customer does not exist.");
        var customer = customerCheck.get();
        return customerMapper.toCustomerDto(customer);
    }

    @SneakyThrows
    @Override
    public CustomerProfileDto getProfile() {
        var userDetails = SecurityUtil.getPrincipal().get();
        var userCheck = userRepository
                .findByUsernameAndIsDeleted(userDetails.getUsername(), false);
        if (userCheck.isEmpty()) throw new DataNotFoundException("User does not exist.");
        var user = userCheck.get();
        var customerCheck = customerRepository.findByUserIdAndIsDeleted(user.getUserId(), false);
        if (customerCheck.isEmpty()) throw new DataNotFoundException("Customer does not exist.");
        var customer = customerCheck.get();
        return customerMapper.toCustomerProfileDto(customer);
    }
}
