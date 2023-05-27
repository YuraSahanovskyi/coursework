package com.example.coursework.controllers;

import com.example.coursework.model.User;
import com.example.coursework.repository.UserRepository;
import com.example.coursework.services.UserService;
import com.example.coursework.services.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserRepository userRepository) {
        this.userService = new UserServiceImpl(userRepository);
    }

    @GetMapping("/account")
    protected String currentUserPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByAuth(authentication);
        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping("/edit")
    protected String saveUserChanges(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByAuth(authentication);
        user.setUsername(username);
        user.setPassword(password);
        if (userService.isExist(username)) {
            String message = "User exist!";
            model.addAttribute("message", message);
        } else {
            userService.add(user);
        }
        return "redirect:/logout";
    }
}
