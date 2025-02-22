package com.gialongchuai.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gialongchuai.identity.entity.InvalidatedToken;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
