package com.kamilabiyev.ecommerce.service.data;

import com.kamilabiyev.ecommerce.domain.model.dto.RoleDto;
import com.kamilabiyev.ecommerce.domain.model.request.role.CreateRoleRequest;
import com.kamilabiyev.ecommerce.domain.model.request.role.UpdateRoleRequest;

import java.util.List;

public interface RoleService {
    Long create(CreateRoleRequest createRoleRequest);

    Long update(Long id, UpdateRoleRequest updateRoleRequest);

    RoleDto getById(Long id);

    List<RoleDto> getAll();

    void delete(Long id);
}
