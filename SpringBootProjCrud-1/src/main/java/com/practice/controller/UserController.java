package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.service.UserService;

@RestController
@RequestMapping("/User/api")
public class UserController {
	@Autowired
	private UserService service;
	@GetMapping("/healthCheckup")
	public String healthCheckup() {
		return service.healthCheck();
	}
}
