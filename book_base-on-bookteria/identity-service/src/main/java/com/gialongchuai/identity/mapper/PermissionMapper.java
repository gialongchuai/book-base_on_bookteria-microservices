package com.gialongchuai.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.gialongchuai.identity.dto.request.PermissionRequest;
import com.gialongchuai.identity.dto.response.PermissionResponse;
import com.gialongchuai.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);

    void updatePermission(@MappingTarget Permission permission, PermissionRequest permissionRequest);
}
