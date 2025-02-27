package com.hasnat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.hasnat.entity.Users;
import com.hasnat.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
	@PostMapping
	public String healthCheck() {
		return "Hello";
	}
	
	public Users addUser(Users u) {
		return repo.save(u);
	}
	public List<Users> getAllUser(){
		return repo.findAll();
	}
	public Optional<Users> getUser(Integer id) {
		return repo.findById(id);
	}
	public Users updateUser(Users u) {
		Optional<Users> byId = repo.findById(u.getId());
		if(byId.isPresent()) {
			Users user = byId.get();
			user.setName(u.getName());
			user.setRole(u.getRole());
			return repo.save(u);
		}
		else throw new RuntimeException("User not found with this credential");
	}
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
}
