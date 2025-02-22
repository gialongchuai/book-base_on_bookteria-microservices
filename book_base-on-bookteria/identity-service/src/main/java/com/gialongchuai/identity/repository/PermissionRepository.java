package com.gialongchuai.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gialongchuai.identity.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    Permission findByName(String name);
}
