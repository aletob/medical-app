package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.MedicalVisit;
import com.thesis.medicalapplication.service.MedicalVisitService;
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
public class MedicalVisitController {

    @Autowired
    MedicalVisitService medicalVisitService;


    @RequestMapping("/allVisits")
    public String getAllVisits(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("visits", medicalVisitService.findAllUserVisits(request.getRemoteUser()));
        return "user/allVisits";
    }

    @RequestMapping("/allFutureVisits")
    public String getAllFutureVisits(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("visits", medicalVisitService.findFutureVisits(request.getRemoteUser()));
        return "user/allVisits";
    }

    @RequestMapping(value = "/deleteVisit")
    public String deleteVisit(@RequestParam("id") Integer id) {
        medicalVisitService.delete(id);
        return "redirect:/user/allVisits";
    }

    @RequestMapping("/addVisit")
    public String addVisitGet(Model model, HttpServletRequest request) {
        model.addAttribute("visit", new MedicalVisit());
        model.addAttribute("user", request.getRemoteUser());
        return "user/addVisit";
    }

    @RequestMapping(value = "/addVisit", method = RequestMethod.POST)
    public String addVisitPost(@Valid MedicalVisit visit, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "user/addVisit";

        } else {
            medicalVisitService.saveVisit(visit, request.getRemoteUser());
            return "redirect:/user/allVisits";
        }
    }


}
