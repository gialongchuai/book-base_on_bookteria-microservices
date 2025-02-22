package com.gialongchuai.identity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.gialongchuai.identity.dto.request.RoleRequest;
import com.gialongchuai.identity.dto.response.ApiResponse;
import com.gialongchuai.identity.dto.response.RoleResponse;
import com.gialongchuai.identity.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(roleRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRole() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRoles())
                .build();
    }

    @DeleteMapping("/{roleName}")
    ApiResponse<RoleResponse> deleteRole(@PathVariable String roleName) {
        roleService.deleteRole(roleName);
        return ApiResponse.<RoleResponse>builder()
                .message("Delete role successfully!")
                .build();
    }
}
