package com.kamilabiyev.ecommerce.service.utils.mapper;

import com.kamilabiyev.ecommerce.domain.model.dto.RoleDto;
import com.kamilabiyev.ecommerce.domain.model.entity.Role;
import com.kamilabiyev.ecommerce.domain.model.request.role.CreateRoleRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    public RoleDto toRoleDto(Role role) {
        return new RoleDto(
                role.getRoleId(),
                role.getName(),
                role.getCreatedAt(),
                role.getUpdatedAt()
        );
    }

    public List<RoleDto> toRoleDto(List<Role> categories) {
        return categories.stream().map(this::toRoleDto).collect(Collectors.toList());
    }

    public Role toRole(CreateRoleRequest roleRequest) {
        return Role.builder().name(roleRequest.getName()).build();
    }
}
