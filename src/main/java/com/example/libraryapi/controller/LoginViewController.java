package com.example.libraryapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {
    @GetMapping("/login")
    public String areaLogin() {
        return "login"; // Retorna o nome da view (login.html)
    }
}
