package com.hasnat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hasnat.entity.Users;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;
	@PostMapping("/healthCheck")
	public String healthCheck() {
		return service.healthCheck();
	}
	@PostMapping("/addUser")
	public Users addUser(@RequestBody Users u) {
		return service.addUser(u);
	}
	
	@PostMapping("/getAllUser")
	public List<Users> getAllUser(){
		return service.getAllUser();
	}
	
	@GetMapping("/getUser")
	public Optional<Users> getUser(@RequestParam Integer id) {
		return service.getUser(id);
	}
	@PostMapping("/update")
	public Users updateUser(@RequestBody Users u) {
		return service.updateUser(u);
	}
	@GetMapping("/delete")
	public void delete(@RequestParam Integer id) {
		service.delete(id);
	}
	
}
