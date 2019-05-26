package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.Mail;
import com.thesis.medicalapplication.model.PasswordResetToken;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PasswordResetTokenService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    UserService userService;

    @Autowired
    MailService emailService;


    public void reset(String email, String url){

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        User user = userService.findUserByEmail(email);
        token.setUser(user);
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 60);
        token.setExpiryDate(now.getTime());
        passwordResetTokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("aplikacja.medyczna@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Restart hasła");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("resetUrl", url + "/resetPassword?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);
    }

    public PasswordResetToken findByToken(String token){
        return passwordResetTokenRepository.findByToken(token);
    }

    public boolean isExpired(PasswordResetToken token) {
        Date expiryDate = token.getExpiryDate();
        return new Date().after(expiryDate);
    }

    public void delete(String token){
        int id = passwordResetTokenRepository.findByToken(token).getToken_id();
        passwordResetTokenRepository.deleteById(id);
    }
}
