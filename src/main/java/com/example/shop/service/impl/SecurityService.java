package com.example.shop.service.impl;

import com.example.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {

    private final UserRepository userRepository;

    public boolean hasAccessToUser(Long userId) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .map(user -> user.getId().equals(userId))
                .orElse(false);

    }
}
