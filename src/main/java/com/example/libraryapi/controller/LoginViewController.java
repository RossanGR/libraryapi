package com.example.libraryapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {
    @GetMapping("/login")
    public String areaLogin() {
        return "login"; // Retorna o nome da view (login.html)
    }

    @GetMapping("/")
    @ResponseBody
    public String paginaHome(Authentication authentication) {
        return "Olá, " + authentication.getName() + "! Bem-vindo à área restrita.";
    }
}
