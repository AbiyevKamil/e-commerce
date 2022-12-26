package com.kamilabiyev.ecommerce.domain.seeder;

import com.kamilabiyev.ecommerce.domain.constant.RoleType;
import com.kamilabiyev.ecommerce.domain.entity.Role;
import com.kamilabiyev.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;

    public void seed() {
        if (!roleRepository.existsByName(RoleType.ADMIN.name()))
            roleRepository.save(Role.builder().name(RoleType.ADMIN.name()).build());
        if (!roleRepository.existsByName(RoleType.SELLER.name()))
            roleRepository.save(Role.builder().name(RoleType.SELLER.name()).build());
        if (!roleRepository.existsByName(RoleType.CUSTOMER.name()))
            roleRepository.save(Role.builder().name(RoleType.CUSTOMER.name()).build());
    }
}
