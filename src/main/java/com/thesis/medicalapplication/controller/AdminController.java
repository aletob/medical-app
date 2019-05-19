package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.UserRepository;
import com.thesis.medicalapplication.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/secure")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BugService bugService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/homepage")
    public String adminPage(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "adminHomepage";
    }

    @GetMapping("/allBugs")
    public String getAllBugs(Model model, HttpServletRequest request){
        model.addAttribute("bugs", bugService.findAll());
        model.addAttribute("user", request.getRemoteUser());
        return "allBugs";
    }

    @GetMapping("/notFixedBugs")
    public String getNotFixedBugs(Model model, HttpServletRequest request){
        model.addAttribute("bugs", bugService.findAllNotFixed());
        model.addAttribute("user", request.getRemoteUser());
        return "allBugs";
    }

    @RequestMapping(value = "/change")
    public String changeBug(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        bugService.changeStatus(id);
        model.addAttribute("bugs", bugService.findAll());
        model.addAttribute("user", request.getRemoteUser());
        return "allBugs";
    }
}
