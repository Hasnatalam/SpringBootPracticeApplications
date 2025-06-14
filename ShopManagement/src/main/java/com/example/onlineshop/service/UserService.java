package com.example.onlineshop.service;

import com.example.onlineshop.dto.UserDTO;
import com.example.onlineshop.model.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User registerUser(UserDTO userDTO);
    User updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
    User getCurrentUser();
	User registerUser(User user);
}