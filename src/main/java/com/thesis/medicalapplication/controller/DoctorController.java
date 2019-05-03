package com.thesis.medicalapplication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    @GetMapping("/homepage")
    public String doctorPage(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "doctorHomepage";
    }
}