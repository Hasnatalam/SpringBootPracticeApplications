package com.example.jwtauth.service;

import com.example.jwtauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j  // Lombok annotation for logging
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> {
                        log.warn("User not found with email: {}", username);
                        return new UsernameNotFoundException("Invalid credentials");
                    });
        } catch (Exception ex) {
            log.error("Error loading user by username: {}", username, ex);
            throw new UsernameNotFoundException("Error during authentication", ex);
        }
    }
}