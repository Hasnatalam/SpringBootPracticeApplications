package com.example.onlineshop.service;

import com.example.onlineshop.dto.UserDTO;
import com.example.onlineshop.model.User;

public interface AuthService {
    User registerUser(UserDTO userDTO);
}