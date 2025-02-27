package com.practice.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	//private UserRepository userRepo;
	
	public String healthCheck() {
		return "Hello";
	}
}
