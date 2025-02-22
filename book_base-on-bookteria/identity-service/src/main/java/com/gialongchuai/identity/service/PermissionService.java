package com.gialongchuai.identity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gialongchuai.identity.dto.request.PermissionRequest;
import com.gialongchuai.identity.dto.response.PermissionResponse;
import com.gialongchuai.identity.entity.Permission;
import com.gialongchuai.identity.mapper.PermissionMapper;
import com.gialongchuai.identity.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toPermission(permissionRequest);

        permission = permissionRepository.save(permission);

        PermissionResponse permissionResponse = permissionMapper.toPermissionResponse(permission);

        return permissionResponse;
    }

    public List<PermissionResponse> getPermissions() {
        var permissions = permissionRepository.findAll();

        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void deletePermission(String permissionName) {
        permissionRepository.deleteById(permissionName);
    }

    public PermissionResponse updatePermission(String permissionName, PermissionRequest permissionRequest) {
        Permission permission = permissionRepository.findByName(permissionName);
        permissionMapper.updatePermission(permission, permissionRequest);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }
}
