package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Medicine;
import com.thesis.medicalapplication.service.MedicineService;
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
public class MedicineController {

    @Autowired
    MedicineService medicineService;

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
}
