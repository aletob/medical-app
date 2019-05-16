package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.repository.UserRepository;
import com.thesis.medicalapplication.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/doctor")
public class DoctorPatientsManagment {

    @Autowired
    DoctorService doctorService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/myPatients")
    public String getPatientsDetails(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        int userId = userRepository.findByUsername(request.getRemoteUser()).getUserId();
        if (doctorService.checkIfAccountEnable(userId)){
            model.addAttribute("message", "Konto aktywne");
            return "doctorHomepage";
        }else {
            model.addAttribute("message", "Konto nieaktywne");
            return "doctorHomepage";
        }
    }
}
