package com.kamilabiyev.ecommerce.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS aa", timezone = "Asia/Baku")
    private Timestamp createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS", timezone = "Asia/Baku")
    private Timestamp updatedAt;
}
