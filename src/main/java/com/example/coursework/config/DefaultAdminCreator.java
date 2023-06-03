package com.example.coursework.config;

import com.example.coursework.model.Role;
import com.example.coursework.model.User;
import com.example.coursework.services.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DefaultAdminCreator implements ApplicationRunner {
    private final UserService userService;

    public DefaultAdminCreator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userService.isExist("admin")) {
            User admin = userService.getByName("admin");
            admin.getRoles().add(Role.ADMIN);
            userService.save(admin);
        } else {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(Role.USER);
            adminRoles.add(Role.ADMIN);
            admin.setRoles(adminRoles);
            admin.setActive(true);
            userService.save(admin);
        }
    }
}
