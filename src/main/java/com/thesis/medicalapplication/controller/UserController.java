package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Bug;
import com.thesis.medicalapplication.model.Medicine;
import com.thesis.medicalapplication.model.Record;
import com.thesis.medicalapplication.service.BugService;
import com.thesis.medicalapplication.service.MedicineService;
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

    @Autowired
    BugService bugService;

    @Autowired
    MedicineService medicineService;

    @GetMapping("/homepage")
    public String process(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        return "userHomepage";
    }

    @GetMapping("/addRecord")
    public String addRecordGet(Model model) {
        Record record = new Record();
        model.addAttribute("record", record);
        return "addRecord";
    }

    @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
    public String addRecordPost(@Valid Record record, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "addRecord";

        } else {
            recordService.saveRecord(record, request.getRemoteUser());
            return "redirect:/user/allRecords";
        }
    }

    @GetMapping("/allRecords")
    public String getAllUsersRecords(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("records", recordService.findRecordsByUsername(request.getRemoteUser()));
        return "allRecords";
    }


    @RequestMapping(value = "/deleteRecord")
    public String deleteRecord(@RequestParam("id") Integer id) {
        recordService.delete(id);
        return "redirect:/user/allRecords";
    }


    @GetMapping("/allMedicines")
    public String getAllMedicines(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("medicines", medicineService.findAllUserMedicines(request.getRemoteUser()));
        return "allMedicines";
    }

    @GetMapping("/allMedicinesCurrent")
    public String getAllCurrentMedicines(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("medicines", medicineService.findCurrentMedicines(request.getRemoteUser()));
        return "allMedicines";
    }

    @RequestMapping(value = "/deleteMedicine")
    public String deleteMedicine(@RequestParam("id") Integer id) {
        medicineService.delete(id);
        return "redirect:/user/allMedicines";
    }

    @GetMapping("/addMedicine")
    public String addMedicineGet(Model model) {
        Medicine medicine = new Medicine();
        model.addAttribute("medicine", medicine);
        return "addMedicine";
    }

    @RequestMapping(value = "/addMedicine", method = RequestMethod.POST)
    public String addMedicinePost(@Valid Medicine medicine, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "addMedicine";

        } else {
            medicineService.saveMedicine(medicine, request.getRemoteUser());
            return "redirect:/user/allMedicines";
        }
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

}
