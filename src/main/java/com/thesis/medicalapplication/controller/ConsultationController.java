package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Consultation;
import com.thesis.medicalapplication.service.ConsultationService;
import com.thesis.medicalapplication.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class ConsultationController {

    @Autowired
    ConsultationService consultationService;

    @Autowired
    DoctorService doctorService;

    @RequestMapping("/allNotAnsweredConsultation")
    public String getNotAnsweredConsultation(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("consultations", consultationService.findAllUserNotAnsweredUserConsultation(request.getRemoteUser()));
        return "user/allConsultations";
    }

    @RequestMapping("/allConsultation")
    public String getAllConsultation(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("consultations", consultationService.findAllUserConsultations(request.getRemoteUser()));
        return "user/allConsultations";
    }

    @RequestMapping("/addConsultation")
    public String addConsultationGet(Model model, HttpServletRequest request) {
        model.addAttribute("consultation", new Consultation());
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("doctors", doctorService.getAllEnabled());
        return "user/addConsultation";
    }

    @RequestMapping(value = "/addConsultation", method = RequestMethod.POST)
    public String addConsultationPost(@Valid Consultation consultation, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "user/addConsultation";

        } else {
            consultationService.saveConsultation(consultation, request.getRemoteUser());
            return "redirect:/user/allConsultation";
        }
    }

    @RequestMapping(value = "/consultation")
    public String doctorDetails(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        Consultation consultation = consultationService.findConsultationById(id);
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("consultation", consultation);
        return "user/consultationDetails";
    }

}
