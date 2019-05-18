package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Bug;
import com.thesis.medicalapplication.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class BugController {

    @Autowired
    BugService bugService;

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
}
