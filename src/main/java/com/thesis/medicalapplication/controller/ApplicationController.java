package com.thesis.medicalapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class ApplicationController {

    @GetMapping("/index")
    public String process() {
        return "Strona glowna";
    }

}
