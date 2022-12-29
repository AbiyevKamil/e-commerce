package com.kamilabiyev.ecommerce.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user;

    private Double balance;

    private String location;
}
