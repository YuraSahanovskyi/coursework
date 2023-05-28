package com.example.coursework.config;

import com.example.coursework.model.Role;
import com.example.coursework.model.User;
import com.example.coursework.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DefaultAdminCreator implements ApplicationRunner {
    private final UserRepository userRepository;

    public DefaultAdminCreator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(Role.USER);
            adminRoles.add(Role.ADMIN);
            admin.setRoles(adminRoles);
            admin.setActive(true);
            userRepository.save(admin);
        }
    }
}
