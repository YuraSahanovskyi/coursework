package com.example.coursework.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;


@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        int statusCode = (int) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("error", "Page Not Found");
            model.addAttribute("message", "The requested page does not exist.");
        } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
            model.addAttribute("error", "Access Denied");
            model.addAttribute("message", "You do not have permission to access this page.");
        }
        else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.addAttribute("error", "Internal Server Error");
            model.addAttribute("message", "An internal server error occurred.");
        } else {
            model.addAttribute("error", "Error");
            model.addAttribute("message", "An error occurred.");
        }
        return "error";
    }
}
