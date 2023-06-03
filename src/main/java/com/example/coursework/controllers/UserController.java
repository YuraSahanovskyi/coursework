package com.example.coursework.controllers;

import com.example.coursework.model.Role;
import com.example.coursework.model.User;
import com.example.coursework.services.UserService;
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

    public UserController(UserService userService) {
        this.userService = userService;
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
        if (userService.isExist(username)) {
            String message = "User exist!";
            model.addAttribute("message", message);
        } else {
            userService.edit(user, username, password);
        }
        return "redirect:/logout";
    }

    @GetMapping("/access")
    protected String getAccessControl(Model model) {
        Iterable<User> users = userService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("adminRole", Role.ADMIN);
        return "users";
    }

    @PostMapping("/save")
    protected String saveRoles(@RequestParam(value = "id") Long id,
                               @RequestParam(value = "isAdmin", required = false) Boolean isAdmin) {
        User user = userService.getById(id);
        if (isAdmin != null && isAdmin) {
            user.getRoles().add(Role.ADMIN);
        } else {
            user.getRoles().remove(Role.ADMIN);
        }
        userService.save(user);
        return "redirect:/user/access";
    }

    @PostMapping("/delete")
    protected String deleteUser(@RequestParam(value = "id") Long id) {
        userService.delete(id);
        return "redirect:/user/access";
    }
}
