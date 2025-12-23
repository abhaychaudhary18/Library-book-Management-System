package com.abhay.calculator.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * After successful login, redirect user to the appropriate dashboard
     * based on role.
     */
    @GetMapping("/post-login")
    public String postLogin(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/user/dashboard";
    }
}


