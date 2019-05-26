package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Niezalogowany");
        return "all/index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("message", "Niezalogowany");
        return "all/mylogin";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("message", "Niezalogowany");
        model.addAttribute("loginerror", true);
        return "all/mylogin";
    }

    @RequestMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("message", "Niezalogowany");
        return "all/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "all/registration";

        }
        else if(userService.isUserAlreadyPresent(user)){
            bindingResult.rejectValue("username", null, "Użytkownik o takim nicku już istnieje");
            return "all/registration";
        }
        else {
            userService.saveUser(user);
            return "redirect:/index/";
        }
    }

    @RequestMapping("/doctorRegistration")
    public String doctorRegistration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("message", "Niezalogowany");
        return "all/doctorRegistration";
    }

    @RequestMapping(value = "/doctorRegistration", method = RequestMethod.POST)
    public String registerDoctor(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "all/doctorRegistration";

        }
        else if(userService.isUserAlreadyPresent(user)){
            bindingResult.rejectValue("username", null, "Użytkownik o takim nicku już istnieje");
            return "all/doctorRegistration";
        }
        else {
            userService.saveUserDoctor(user);
            return "redirect:/index/";
        }
    }

}
