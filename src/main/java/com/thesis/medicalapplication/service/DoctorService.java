package com.thesis.medicalapplication.service;


import com.thesis.medicalapplication.model.Doctor;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.DoctorRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public void saveDoctor(Doctor doctor, String username){
        User user = userRepository.findByUsername(username);
        doctor.setUser(user);
        doctor.setEnable(false);
        doctorRepository.save(doctor);
    }

    public Doctor getDoctorByUsername(String username){
        return userRepository.findByUsername(username).getDoctor();
    }

}
