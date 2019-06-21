package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.BloodPressureResult;
import com.thesis.medicalapplication.model.BloodResult;
import com.thesis.medicalapplication.model.GeneralResult;
import com.thesis.medicalapplication.service.BloodPressureResultService;
import com.thesis.medicalapplication.service.BloodResultService;
import com.thesis.medicalapplication.service.GeneralResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/user")

public class ResultController {

    @Autowired
    BloodResultService bloodResultService;

    @Autowired
    BloodPressureResultService bloodPressureResultService;

    @Autowired
    GeneralResultService generalResultService;


    // ---------------------- BloodResult ----------------------

    @RequestMapping("/allBloodResults")
    public String getAllUserBloodResult(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("bloodResults", bloodResultService.findResultsByUsername(request.getRemoteUser()));
        model.addAttribute("parameters", bloodResultService.findUnionParameters(request.getRemoteUser()));

        return "user/allBloodResults";
    }

    @RequestMapping("/addBloodResult")
    public String addBloodResultGet(Model model, HttpServletRequest request) {
        model.addAttribute("bloodResult", new BloodResult());
        model.addAttribute("user", request.getRemoteUser());
        return "user/addBloodResult";
    }

    @RequestMapping(value = "/addBloodResult", method = RequestMethod.POST)
    public String addBloodResultPost(@Valid BloodResult bloodResult, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "user/addBloodResult";

        } else {
            bloodResultService.saveResult(bloodResult, request.getRemoteUser());
            return "redirect:/user/allBloodResults";
        }
    }

    @RequestMapping(value = "/deleteBloodResult")
    public String deleteBloodResult(@RequestParam("id") Integer id) {
        bloodResultService.delete(id);
        return "redirect:/user/allBloodResults";
    }

    @RequestMapping(value = "/allBloodResults", method = RequestMethod.POST)
    public String allBloodResultsPost(@RequestParam String parameter, HttpServletRequest request, Model model) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("parameters", bloodResultService.findUnionParameters(request.getRemoteUser()));

        if (parameter == null) {
            model.addAttribute("bloodResults", bloodResultService.findResultsByUsername(request.getRemoteUser()));
        } else {
            model.addAttribute("filtred", true);
            model.addAttribute("bloodResults", bloodResultService.findBloodResultByParameter(parameter, request.getRemoteUser()));
        }
        return "user/allBloodResults";
    }


    // ---------------------- BloodPressureResult ----------------------

    @RequestMapping("/allBloodPressureResults")
    public String getAllUserBloodPressureResult(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("bloodPressureResults", bloodPressureResultService.findResultsByUsername(request.getRemoteUser()));
        return "user/allBloodPressureResults";
    }

    @RequestMapping("/addBloodPressureResult")
    public String addBloodPressureResultGet(Model model, HttpServletRequest request) {
        model.addAttribute("bloodPressureResult", new BloodPressureResult());
        model.addAttribute("user", request.getRemoteUser());
        return "user/addBloodPressureResult";
    }

    @RequestMapping(value = "/addBloodPressureResult", method = RequestMethod.POST)
    public String addBloodPressureResultPost(@Valid BloodPressureResult bloodPressureResult, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "user/addBloodPressureResult";

        } else {
            bloodPressureResultService.saveResult(bloodPressureResult, request.getRemoteUser());
            return "redirect:/user/allBloodPressureResults";
        }
    }

    @RequestMapping(value = "/deleteBloodPressureResult")
    public String deleteBloodPressureResult(@RequestParam("id") Integer id) {
        bloodPressureResultService.delete(id);
        return "redirect:/user/allBloodPressureResults";
    }

    @RequestMapping(value = "displayGraph")
    public String graph(Model model, HttpServletRequest request){
        List<BloodPressureResult> results = bloodPressureResultService.findResultsWithDate(request.getRemoteUser());
        Map<Date, Integer> mapDiastolic = new LinkedHashMap<>();
        Map<Date, Integer> mapSystolic = new LinkedHashMap<>();

        for(BloodPressureResult result: results){
            mapDiastolic.put(result.getDate(), result.getDiastolic());
            mapSystolic.put(result.getDate(), result.getSystolic());
        }

        model.addAttribute("diastolic", mapDiastolic);
        model.addAttribute("systolic", mapSystolic);
        model.addAttribute("user", request.getRemoteUser());

        return "user/bloodPressureChart";
    }


    // ---------------------- GeneralResult ----------------------

    @RequestMapping("/allGeneralResults")
    public String getAllGeneralResult(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("generalResults", generalResultService.findResultsByUsername(request.getRemoteUser()));
        return "user/allGeneralResults";
    }

    @RequestMapping("/addGeneralResult")
    public String addGeneralResultGet(Model model, HttpServletRequest request) {
        model.addAttribute("generalResult", new GeneralResult());
        model.addAttribute("user", request.getRemoteUser());
        return "user/addGeneralResult";
    }

    @RequestMapping(value = "/addGeneralResult", method = RequestMethod.POST)
    public String addGeneralResultPost(@Valid GeneralResult generalResult, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "user/addGeneralResult";

        } else {
            generalResultService.saveResult(generalResult, request.getRemoteUser());
            return "redirect:/user/allGeneralResults";
        }
    }

    @RequestMapping(value = "/deleteGeneralResult")
    public String deleteGeneralResult(@RequestParam("id") Integer id) {
        generalResultService.delete(id);
        return "redirect:/user/allGeneralResults";
    }

}