package com.kamilabiyev.ecommerce.controller;

import com.kamilabiyev.ecommerce.domain.model.request.customer.UpdateCustomerRequest;
import com.kamilabiyev.ecommerce.service.data.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity getProfile() {
        return ResponseEntity.ok().body(customerService.getProfile());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(customerService.getById(id));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity update(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        customerService.update(updateCustomerRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity delete() {
        customerService.delete();
        return ResponseEntity.ok().build();
    }
}
