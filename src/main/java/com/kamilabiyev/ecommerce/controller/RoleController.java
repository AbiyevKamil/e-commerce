package com.kamilabiyev.ecommerce.controller;

import com.kamilabiyev.ecommerce.domain.model.request.role.CreateRoleRequest;
import com.kamilabiyev.ecommerce.domain.model.request.role.UpdateRoleRequest;
import com.kamilabiyev.ecommerce.service.data.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @SecurityRequirements
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(roleService.getAll());
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(roleService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity create(@Valid @RequestBody CreateRoleRequest createRoleRequest) {
        return ResponseEntity.ok().body(roleService.create(createRoleRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity update(@Valid @RequestBody UpdateRoleRequest updateRoleRequest, @PathVariable Long id) {
        return ResponseEntity.ok().body(roleService.update(id, updateRoleRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
