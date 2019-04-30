package com.thesis.medicalapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")

public class UserController {

    @GetMapping("/homepage")
    public String process(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "user_homepage";
    }

}
