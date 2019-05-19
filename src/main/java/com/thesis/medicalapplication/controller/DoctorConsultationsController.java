package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Consultation;
import com.thesis.medicalapplication.service.ConsultationService;
import com.thesis.medicalapplication.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/doctor")
public class DoctorConsultationsController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    ConsultationService consultationService;

    @GetMapping("/myConsultations")
    public String getAllConsultations(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("consultations", consultationService.findAllDoctorConsultations(request.getRemoteUser()));
        return "doctorConsultations";
    }

    @GetMapping("/myNotAnsweredConsultations")
    public String getNotAnsweredConsultations(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("consultations", consultationService.findNotAnsweredDoctorConsultations(request.getRemoteUser()));
        return "doctorConsultations";
    }

    @RequestMapping(value = "/consultation")
    public String doctorDetails(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        Consultation consultation = consultationService.findConsultationById(id);
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("consultation", consultation);
        return "doctorConsultationDetails";
    }

    @RequestMapping(value = "/consultationAnswer", method = RequestMethod.POST)
    public String consultationAnswer(@Valid Consultation consultation, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "doctorConsultationDetails";

        } else {
            consultationService.saveConsultation(consultation, request.getRemoteUser());
            return "redirect:/doctor/myConsultations";
        }
    }

}
