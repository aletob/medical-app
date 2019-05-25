package com.thesis.medicalapplication.controller;

import com.thesis.medicalapplication.model.PasswordResetToken;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.service.PasswordResetTokenService;
import com.thesis.medicalapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/resetPassword")
public class PasswordResetController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordResetTokenService tokenService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @RequestMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        PasswordResetToken resetToken = tokenService.findByToken(token);
        if (resetToken == null) {
            model.addAttribute("error", "Nie odnaleziono tokena.");
        } else if (tokenService.isExpired(resetToken)) {
            model.addAttribute("error", "Token wygasł, spróbuj ponownie zrestartować hasło.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }
        return "resetPassword";
    }


    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public String handlePasswordReset(@RequestParam String password, String token,
                                      Model model) {


        if (password == null || password.length() <5) {
            model.addAttribute("errorMessage", "Nieprawidłowe hasło (min. 5 znaków)");
            model.addAttribute("token", token);
            return "resetPassword";
        }

        PasswordResetToken tokenDB = tokenService.findByToken(token);
        User user = tokenDB.getUser();
        userService.updatePassword(password, user.getUserId());
        tokenService.delete(token);

        return "redirect:/index/login";
    }
}
