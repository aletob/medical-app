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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /*@PreAuthorize("hasRole('ROLE_ADMIN')")*/
    @PostMapping("/admin/add")
    public String addUserByAdmin(@RequestBody User user) {
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        userRepository.save(user);
        return "User added successfully!";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/homepage")
    public String adminPage(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "adminHomepage";
    }

    @GetMapping("/allBugs")
    public String getAllBugs(Model model, HttpServletRequest request){
        model.addAttribute("bugs", bugService.findAllNotFixed());
        model.addAttribute("user", request.getRemoteUser());
        return "allBugs";
    }
}
