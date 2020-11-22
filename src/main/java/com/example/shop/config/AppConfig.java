package com.example.shop.config;

import com.example.shop.domain.dao.Role;
import com.example.shop.repository.jpa.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
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

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .globalOperationParameters(Collections.singletonList(new ParameterBuilder()
                .name("Authorization")
                .modelRef(new ModelRef("string"))
                        .required(false)
                        .parameterType("header")
                        .build()

                ))
                .select()
                .paths(PathSelectors.any())
                .build();
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
