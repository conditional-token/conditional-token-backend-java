package com.matching.bet.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matching.bet.domain.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
