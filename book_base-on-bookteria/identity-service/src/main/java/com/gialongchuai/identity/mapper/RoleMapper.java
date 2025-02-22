package com.gialongchuai.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gialongchuai.identity.dto.request.RoleRequest;
import com.gialongchuai.identity.dto.response.RoleResponse;
import com.gialongchuai.identity.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
