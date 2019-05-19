package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorPatientsController {

    @Autowired
    UserService userService;

    @Autowired
    PatientService patientService;

    @Autowired
    ConsultationService consultationService;

    @Autowired
    BloodResultService bloodResultService;

    @Autowired
    BloodPressureResultService bloodPressureResultService;

    @GetMapping("/allPatients")
    public String getAllPatients(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        List<User> allUsers = userService.findUsersForDoctor(request.getRemoteUser());
        model.addAttribute("users", allUsers);
        return "allPatients";
    }

    @RequestMapping(value = "/patientDetails")
    public String patientDetails(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("patient", patientService.getPatientByUserId(id));
        return "patientDetails";
    }

    @RequestMapping(value = "/patientBloodResults")
    public String patientResults(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("results", bloodResultService.findResultsByUserId(id));
        return "patientBloodResults";
    }

    @RequestMapping(value = "/patientBloodPressureResults")
    public String patientBloodPressureResults(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("results", bloodPressureResultService.findResultsByUserId(id));
        return "patientBloodPressureResults";
    }

    @RequestMapping(value = "/consultationHistory")
    public String consultationHistory(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("consultations", consultationService.findConsultationHistory(request.getRemoteUser(), id));
        return "consultationHistory";
    }

}
