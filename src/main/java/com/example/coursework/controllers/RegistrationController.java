package com.example.coursework.controllers;

import com.example.coursework.model.Role;
import com.example.coursework.model.User;
import com.example.coursework.repository.UserRepository;
import com.example.coursework.services.UserService;
import com.example.coursework.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserRepository userRepository) {
        this.userService = new UserServiceImpl(userRepository);
    }

    @GetMapping("/registration")
    protected String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    protected String registerUser(User user, Map<String, Object> model) {
        if (userService.isExist(user)) {
            model.put("message","User exist!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.add(user);
        return "redirect:/login";
    }
}
