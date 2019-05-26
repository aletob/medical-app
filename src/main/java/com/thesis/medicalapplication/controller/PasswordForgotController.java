package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.service.PasswordResetTokenService;
import com.thesis.medicalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/index")
public class PasswordForgotController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordResetTokenService tokenService;


    @RequestMapping("/forgotPassword")
    public String displayForgotPasswordPage(Model model) {
        model.addAttribute("message", "Niezalogowany");
        return "all/forgotPassword";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String processForgotPasswordForm(@RequestParam String email,
                                            Model model,
                                            HttpServletRequest request) {

        User user = userService.findUserByEmail(email);
        if (user == null) {
            model.addAttribute("errorMessage", "Nie znaleziono użytkownika o takim adresie email");
            return "all/forgotPassword";
        }
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        tokenService.reset(email, url);
        return "redirect:/index/forgotPassword?success";
    }
}
