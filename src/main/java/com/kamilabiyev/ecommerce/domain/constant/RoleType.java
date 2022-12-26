package com.kamilabiyev.ecommerce.domain.constant;

public enum RoleType {
    ADMIN("ADMIN"),
    SELLER("SELLER"),
    CUSTOMER("CUSTOMER");

    private String name;

    RoleType(String name) {
        this.name = name;
    }
}
