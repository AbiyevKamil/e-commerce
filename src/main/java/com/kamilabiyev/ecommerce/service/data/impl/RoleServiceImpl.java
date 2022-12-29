package com.kamilabiyev.ecommerce.service.data.impl;

import com.kamilabiyev.ecommerce.domain.exception.DataExistsException;
import com.kamilabiyev.ecommerce.domain.exception.DataNotFoundException;
import com.kamilabiyev.ecommerce.domain.model.dto.RoleDto;
import com.kamilabiyev.ecommerce.domain.model.entity.Role;
import com.kamilabiyev.ecommerce.domain.model.request.role.CreateRoleRequest;
import com.kamilabiyev.ecommerce.domain.model.request.role.UpdateRoleRequest;
import com.kamilabiyev.ecommerce.repository.RoleRepository;
import com.kamilabiyev.ecommerce.service.data.RoleService;
import com.kamilabiyev.ecommerce.service.utils.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @SneakyThrows
    @Override
    public Long create(CreateRoleRequest createRoleRequest) {
        var roleCheck = roleRepository.findByName(createRoleRequest.getName());
        if (roleCheck.isPresent())
            throw new DataExistsException("Role: "
                    + createRoleRequest.getName() + "is already exists.");
        var role = roleRepository.save(Role.builder()
                .name(createRoleRequest.getName()).build());
        return role.getRoleId();
    }

    @SneakyThrows
    @Override
    public Long update(Long id, UpdateRoleRequest updateRoleRequest) {
        var roleCheck = roleRepository.findByName(updateRoleRequest.getName());
        if (roleCheck.isEmpty())
            throw new DataNotFoundException("Role#"
                    + id + "is not exists.");
        var role = roleCheck.get();
        role.setName(updateRoleRequest.getName());
        roleRepository.save(role);
        return role.getRoleId();
    }

    @SneakyThrows
    @Override
    public RoleDto getById(Long id) {
        var roleCheck = roleRepository.findById(id);
        if (roleCheck.isEmpty())
            throw new DataNotFoundException("Role#"
                    + id + "is not exists.");
        return roleMapper.toRoleDto(roleCheck.get());
    }

    @Override
    public List<RoleDto> getAll() {
        var roles = roleRepository.findAll();
        return roleMapper.toRoleDto(roles);
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        var roleCheck = roleRepository.findById(id);
        if (roleCheck.isEmpty())
            throw new DataNotFoundException("Role#"
                    + id + "is not exists.");
        roleRepository.deleteById(id);
    }
}
