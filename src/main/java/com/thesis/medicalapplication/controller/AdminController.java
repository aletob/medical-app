package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/secure")
public class AdminController {

    @Autowired
    BugService bugService;


    @RequestMapping("/homepage")
    public String adminPage(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "admin/adminHomepage";
    }

    @RequestMapping("/allBugs")
    public String getAllBugs(Model model, HttpServletRequest request){
        model.addAttribute("bugs", bugService.findAll());
        model.addAttribute("user", request.getRemoteUser());
        return "admin/allBugs";
    }

    @RequestMapping("/notFixedBugs")
    public String getNotFixedBugs(Model model, HttpServletRequest request){
        model.addAttribute("bugs", bugService.findAllNotFixed());
        model.addAttribute("user", request.getRemoteUser());
        return "admin/allBugs";
    }

    @RequestMapping(value = "/change")
    public String changeBug(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        bugService.changeStatus(id);
        model.addAttribute("bugs", bugService.findAll());
        model.addAttribute("user", request.getRemoteUser());
        return "admin/allBugs";
    }
}
