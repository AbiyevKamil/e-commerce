package com.kamilabiyev.ecommerce.domain.constant;


public enum BalanceValue {
    ZERO(0D);
    private Double value;

    public Double getValue() {
        return value;
    }

    BalanceValue(Double value) {
        this.value = value;
    }
}
