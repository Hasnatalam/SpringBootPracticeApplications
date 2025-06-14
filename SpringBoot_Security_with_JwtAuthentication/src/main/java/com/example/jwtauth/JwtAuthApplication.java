package com.example.jwtauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args);
		

//
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
//		System.out.println("New secret key: " + base64Key);
		
		
	}

}
