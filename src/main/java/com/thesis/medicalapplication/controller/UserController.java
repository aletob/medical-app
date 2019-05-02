package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Record;
import com.thesis.medicalapplication.service.RecordService;
import com.thesis.medicalapplication.service.UserService;
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
    RecordService recordService;

    @Autowired
    UserService userService;

    @GetMapping("/homepage")
    public String process(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "user_homepage";
    }

    @GetMapping("/addRecord")
    public String addRecord(Model model) {
        Record record = new Record();
        model.addAttribute("record", record);
        return "add_record";
    }

    @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
    public String add(@Valid Record record, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "add_record";

        } else {
            recordService.saveRecord(record, request.getRemoteUser());
            return "redirect:/user/homepage";
        }
    }

/*    @GetMapping("/allRecords")
    public String getAllRecords(Model model){
        model.addAttribute("records", recordService.findAll());
        return "all_records";
    }*/

    @GetMapping("/allRecords")
    public String getAllUsersRecords(Model model, HttpServletRequest request){
        model.addAttribute("records", recordService.findRecordsByUsername(request.getRemoteUser()));
        return "all_records";
    }

}
