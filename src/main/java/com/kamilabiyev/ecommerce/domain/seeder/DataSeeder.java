package com.kamilabiyev.ecommerce.domain.seeder;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DataSeeder {
    RoleSeeder roleSeeder;

    public void seed() {
        roleSeeder.seed();
    }
}
