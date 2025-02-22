package com.gialongchuai.identity.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gialongchuai.identity.dto.request.RoleRequest;
import com.gialongchuai.identity.dto.response.RoleResponse;
import com.gialongchuai.identity.entity.Role;
import com.gialongchuai.identity.mapper.RoleMapper;
import com.gialongchuai.identity.repository.PermissionRepository;
import com.gialongchuai.identity.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest roleRequest) {
        // Chỉ map cái name với des không map thêm cái Permission
        Role role = roleMapper.toRole(roleRequest);

        // Tìm String cái permission xong bat dua map permission
        role.setPermissions(new HashSet<>(permissionRepository.findAllById(roleRequest.getPermissions())));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAllRoles() {
        var role = roleRepository.findAll();
        return role.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void deleteRole(String roleName) {
        roleRepository.deleteById(roleName);
    }
}
