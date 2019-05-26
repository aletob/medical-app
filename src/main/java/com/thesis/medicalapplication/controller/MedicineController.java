package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.Medicine;
import com.thesis.medicalapplication.service.MedicineService;
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
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @RequestMapping("/allMedicines")
    public String getAllMedicines(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("medicines", medicineService.findAllUserMedicines(request.getRemoteUser()));
        return "user/allMedicines";
    }

    @RequestMapping("/allMedicinesCurrent")
    public String getAllCurrentMedicines(Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("medicines", medicineService.findCurrentMedicines(request.getRemoteUser()));
        return "user/allMedicines";
    }

    @RequestMapping(value = "/deleteMedicine")
    public String deleteMedicine(@RequestParam("id") Integer id) {
        medicineService.delete(id);
        return "redirect:/user/allMedicines";
    }

    @RequestMapping("/addMedicine")
    public String addMedicineGet(Model model, HttpServletRequest request) {
        Medicine medicine = new Medicine();
        model.addAttribute("medicine", medicine);
        model.addAttribute("user", request.getRemoteUser());
        return "user/addMedicine";
    }

    @RequestMapping(value = "/addMedicine", method = RequestMethod.POST)
    public String addMedicinePost(@Valid Medicine medicine, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "user/addMedicine";

        } else {
            medicineService.saveMedicine(medicine, request.getRemoteUser());
            return "redirect:/user/allMedicines";
        }
    }
}
