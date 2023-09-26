package com.userapi.repository;

import com.userapi.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole,Integer> {
        Optional<UserRole> findByName(String firstName);
}
