package com.gamesstorebe.repository;

import com.gamesstorebe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(String roleName);
}
