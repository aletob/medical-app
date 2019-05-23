package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Doctor;
import com.thesis.medicalapplication.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    @GetMapping("/homepage")
    public String doctorPage(Model model, HttpServletRequest request) {
        model.addAttribute("enable", doctorService.checkIfAccountEnable(request.getRemoteUser()));
        model.addAttribute("dataCompleted", doctorService.checkIfDataFill(request.getRemoteUser()));
        model.addAttribute("user", request.getRemoteUser());
        return "doctorHomepage";
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    @GetMapping("/account")
    public String doctorAccount(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("doctor", doctorService.getDoctorByUsername(request.getRemoteUser()));
        return "doctorAccount";
    }

    @GetMapping("/accountAdd")
    public String doctorAccountSave(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("doctor", new Doctor());
        return "doctorForm";
    }


    @RequestMapping(value = "/accountAdd", method = RequestMethod.POST)
    public String doctorAccountPost(@Valid Doctor doctor, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "doctorForm";

        } else {
            doctorService.saveDoctor(doctor, request.getRemoteUser());
            return "redirect:/doctor/account";
        }
    }

    @GetMapping("/accountEdit")
    public String doctorAccountEdit(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("doctor", doctorService.getDoctorByUsername(request.getRemoteUser()));
        return "doctorForm";
    }
}