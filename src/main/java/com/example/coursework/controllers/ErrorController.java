package com.example.coursework.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ErrorController {
    @GetMapping("/access-denied")
    protected String handleAccessDenied() {
        return "errors/accessDenied";
    }
}
