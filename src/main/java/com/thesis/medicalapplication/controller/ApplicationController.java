package com.thesis.medicalapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/index")
public class ApplicationController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Witaj nowy użytkowniku");
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "mylogin";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginerror", true);
        return "mylogin";
    }

}
