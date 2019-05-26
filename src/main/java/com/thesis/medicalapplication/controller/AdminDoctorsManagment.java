package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Doctor;
import com.thesis.medicalapplication.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/secure")
public class AdminDoctorsManagment {

    @Autowired
    DoctorService doctorService;

    @RequestMapping("/disabledAccounts")
    public String getAllDisabledAccounts(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("doctors", doctorService.getAllDiasabled());
        return "admin/disabledAccounts";
    }

    @RequestMapping(value = "/doctor")
    public String doctorDetails(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("doctor", doctor);
        return "admin/secureDoctorView";
    }

    @RequestMapping(value = "/enable")
    public String enableAccount(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        doctorService.enableAccount(id);
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("doctors", doctorService.getAllDiasabled());
        return "admin/disabledAccounts";
    }
}
