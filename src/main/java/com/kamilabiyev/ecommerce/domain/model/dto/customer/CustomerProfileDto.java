package com.kamilabiyev.ecommerce.domain.model.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfileDto {
    private Long id;

    private String fullName;

    private Double balance;
    private String email;
    private String username;

    private String location;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS aa", timezone = "Asia/Baku")
    private Timestamp createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS aa", timezone = "Asia/Baku")
    private Timestamp updatedAt;
}
