package com.example.coursework.controllers;

import com.example.coursework.model.Role;
import com.example.coursework.model.User;
import com.example.coursework.services.UserService;
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
    private final int MIN_USERNAME_LENGTH;
    private final int MIN_PASSWORD_LENGTH;

    public RegistrationController(UserService userService) {
        this.userService = userService;
        MIN_USERNAME_LENGTH = 3;
        MIN_PASSWORD_LENGTH = 5;
    }

    @GetMapping()
    protected String registration() {
        return "registration";
    }

    @PostMapping()
    protected String registerUser(@RequestParam String username,
                                  @RequestParam String password,
                                  Map<String, Object> model) {
        if (userService.isExist(username)) {
            model.put("message", "User exist!");
            return "registration";
        }
        if (username == null || username.length() < MIN_USERNAME_LENGTH) {
            model.put("message", "Username must be longer than " + MIN_USERNAME_LENGTH);
            return "registration";
        }
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            model.put("message", "Password must be longer than " + MIN_PASSWORD_LENGTH);
            return "registration";
        }
        User user = new User(username, password, true, Collections.singleton(Role.USER));
        userService.save(user);
        return "redirect:/login";
    }
}
