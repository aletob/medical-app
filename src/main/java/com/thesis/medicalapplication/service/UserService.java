package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.Role;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.model.User_role;
import com.thesis.medicalapplication.repository.RoleRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import com.thesis.medicalapplication.repository.UserRoleRepository;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public void saveUser(User user){
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            User_role user_role = new User_role();
            user_role.setRole(roleRepository.findByRole("USER"));
            user_role.setUser(user);
            userRoleRepository.save(user_role);

            //Role userRole = ;
            //user.setUser_roles(new HashSet<User_role>(44, 1));
           // user.setUser_roles(new HashSet<User_role>(Arrays.asList(userRole)));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isUserAlreadyPresent(User user){
        return false;
    }
}
