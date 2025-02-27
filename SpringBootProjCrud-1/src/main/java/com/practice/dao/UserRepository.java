package com.practice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
}
