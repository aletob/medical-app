package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.model.UserRole;
import com.thesis.medicalapplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    BloodResultRepository bloodResultRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }


    @Transactional
    public void saveUser(User user) {
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            UserRole userRole = new UserRole();
            userRole.setRole(roleRepository.findByRoleDescription("USER"));
            userRole.setUser(user);
            userRoleRepository.save(userRole);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void saveUserDoctor(User user) {
        saveUser(user);

        UserRole userRole = new UserRole();
        userRole.setRole(roleRepository.findByRoleDescription("DOCTOR"));
        userRole.setUser(user);
        userRoleRepository.save(userRole);

    }

    public boolean isUserAlreadyPresent(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> findUsersForDoctor(String username){
        int doctorId = doctorService.getDoctorByUsername(username).getDoctorId();
        return userRepository.findUserForDoctor(doctorId);
    }

    public void updatePassword(String password, int userId) {
        User userDB = userRepository.findUserById(userId);
        userDB.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(userDB);
    }

}
