package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.MedicalVisit;
import com.thesis.medicalapplication.service.MedicalVisitService;
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
@RequestMapping("/user")
public class MedicalVisitController {

    @Autowired
    MedicalVisitService medicalVisitService;


    @GetMapping("/allVisits")
    public String getAllVisits(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("visits", medicalVisitService.findAllUserVisits(request.getRemoteUser()));
        return "allVisits";
    }

    @GetMapping("/allFutureVisits")
    public String getAllFutureVisits(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("visits", medicalVisitService.findAllUserVisits(request.getRemoteUser()));
        return "allVisits";
    }

    @RequestMapping(value = "/deleteVisit")
    public String deleteMedicine(@RequestParam("id") Integer id) {
        medicalVisitService.delete(id);
        return "redirect:/user/allVisits";
    }

    @GetMapping("/addVisit")
    public String addMedicineGet(Model model) {
        model.addAttribute("visit", new MedicalVisit());
        return "addVisit";
    }

    @RequestMapping(value = "/addVisit", method = RequestMethod.POST)
    public String addMedicinePost(@Valid MedicalVisit visit, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "addVisit";

        } else {
            medicalVisitService.saveVisit(visit, request.getRemoteUser());
            return "redirect:/user/allVisits";
        }
    }


}
