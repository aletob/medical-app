package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.BloodResult;
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

public class ResultController {

    @Autowired
    BloodResultService bloodResultService;

    @Autowired
    UserService userService;


    @GetMapping("/addBloodResult")
    public String addBloodResultGet(Model model, HttpServletRequest request) {
        BloodResult bloodResult = new BloodResult();
        model.addAttribute("bloodResult", bloodResult);
        model.addAttribute("user", request.getRemoteUser());
        return "addBloodResult";
    }

    @RequestMapping(value = "/addBloodResult", method = RequestMethod.POST)
    public String addBloodResultPost(@Valid BloodResult bloodResult, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "addBloodResult";

        } else {
            bloodResultService.saveResult(bloodResult, request.getRemoteUser());
            return "redirect:/user/allBloodResults";
        }
    }

    @GetMapping("/allBloodResults")
    public String getAllUserBloodResult(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("bloodResults", bloodResultService.findResultsByUsername(request.getRemoteUser()));
        return "allBloodResults";
    }


    @RequestMapping(value = "/deleteBloodResult")
    public String deleteBloodResult(@RequestParam("id") Integer id) {
        bloodResultService.delete(id);
        return "redirect:/user/allBloodResults";
    }


}
