package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class ConsultationController {

    @Autowired
    ConsultationService consultationService;

    @GetMapping("/allConsultation")
    public String getAllConsultation(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("consultations", consultationService.findAllUserConsultations(request.getRemoteUser()));
        return "allConsultations";
    }

}
