package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Bug;
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
    BloodResultService bloodResultService;

    @Autowired
    UserService userService;

    @Autowired
    BugService bugService;

    @Autowired
    MedicineService medicineService;

    @Autowired
    PatientService patientService;

    @GetMapping("/homepage")
    public String process(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "userHomepage";
    }

    @GetMapping("/addBug")
    public String addBugGet(Model model, HttpServletRequest request) {
        Bug bug = new Bug();
        model.addAttribute("bug", bug);
        model.addAttribute("user", request.getRemoteUser());
        return "addBug";
    }

    @RequestMapping(value = "/addBug", method = RequestMethod.POST)
    public String addBugPost(@Valid Bug bug, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "addBug";

        } else {
            bugService.saveBug(bug, request.getRemoteUser());
            return "redirect:/user/homepage";
        }
    }

    @GetMapping("/patientAccount")
    public String patientAccount(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("patient", patientService.getPatientByUsername(request.getRemoteUser()));
        return "patientAccount";
    }

    @GetMapping("/patientAccountAdd")
    public String patientAccountSave(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("patient", new Patient());
        return "patientForm";
    }

    @RequestMapping(value = "/patientAccountAdd", method = RequestMethod.POST)
    public String patientAccountPost(@Valid Patient patient, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "patientForm";

        } else {
            patientService.savePatient(patient, request.getRemoteUser());
            return "redirect:/user/patientAccount";
        }
    }


}
