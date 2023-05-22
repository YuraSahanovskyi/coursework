package com.example.coursework.controllers;

import com.example.coursework.model.Role;
import com.example.coursework.model.User;
import com.example.coursework.repository.UserRepository;
import com.example.coursework.services.UserService;
import com.example.coursework.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserRepository userRepository) {
        this.userService = new UserServiceImpl(userRepository);
    }

    @GetMapping()
    protected String registration() {
        return "registration";
    }
    @PostMapping()
    protected String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String role, Map<String, Object> model) {
        if (userService.isExist(username)) {
            model.put("message","User exist!");
            return "registration";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.valueOf(role.toUpperCase().trim())));
        userService.add(user);
        return "redirect:/login";
    }
}
