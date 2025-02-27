package com.hasnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hasnat.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

}
