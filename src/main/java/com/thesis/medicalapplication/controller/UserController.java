package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Patient;
import com.thesis.medicalapplication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")

public class UserController {

    @Autowired
    PatientService patientService;

    @RequestMapping("/homepage")
    public String process(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "user/userHomepage";
    }

    @RequestMapping("/patientAccount")
    public String patientAccount(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("patient", patientService.getPatientByUsername(request.getRemoteUser()));
        return "user/patientAccount";
    }

    @RequestMapping("/patientAccountAdd")
    public String patientAccountSave(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("patient", new Patient());
        return "user/patientForm";
    }

    @RequestMapping(value = "/patientAccountAdd", method = RequestMethod.POST)
    public String patientAccountPost(@Valid Patient patient, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "user/patientForm";

        } else {
            patientService.savePatient(patient, request.getRemoteUser());
            return "redirect:/user/patientAccount";
        }
    }

    @RequestMapping("/patientAccountEdit")
    public String patientAccountEdit(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("patient", patientService.getPatientByUsername(request.getRemoteUser()));
        return "user/patientForm";
    }


}
