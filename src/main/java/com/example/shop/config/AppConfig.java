package com.example.shop.config;

import com.example.shop.domain.dao.Role;
import com.example.shop.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            createRoleIfEmpty("ROLE_USER", roleRepository);
            createRoleIfEmpty("ROLE_ADMIN", roleRepository);
        };
    }

    private void createRoleIfEmpty(String roleName, RoleRepository roleRepository){
        Optional<Role> role = roleRepository.findByName(roleName);
        if (role.isEmpty()) {
            roleRepository.save(Role.builder()
                    .name(roleName)
                    .build());
        }

    }
}
