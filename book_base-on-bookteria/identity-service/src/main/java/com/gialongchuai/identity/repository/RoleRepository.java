package com.gialongchuai.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gialongchuai.identity.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);
}
