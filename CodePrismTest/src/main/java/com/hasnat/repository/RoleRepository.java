package com.hasnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hasnat.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
}
