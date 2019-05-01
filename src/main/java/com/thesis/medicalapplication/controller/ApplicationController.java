package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/index")
public class ApplicationController {

    @Autowired
    UserService userService;

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

    @RequestMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "registration";

        } else {
            userService.saveUser(user);
            return "redirect:/index/";
        }
    }

}
