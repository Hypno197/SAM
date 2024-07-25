package com.generation.italy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.italy.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
