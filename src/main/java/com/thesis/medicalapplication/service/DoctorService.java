package com.thesis.medicalapplication.service;


import com.thesis.medicalapplication.model.Doctor;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.DoctorRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Doctor getDoctorById(int id){
        return doctorRepository.findDoctorByDoctorId(id);
    }

    public Doctor getDoctorByUsername(String username){
        return userRepository.findByUsername(username).getDoctor();
    }

    public List<Doctor> getAllDiasabled(){
        return doctorRepository.findAllDisabledDoctors();
    }

    public List<Doctor> getAllEnabled(){
        return doctorRepository.findAllEnabledDoctors();
    }

    public void enableAccount(int id){
        Doctor doctor = doctorRepository.findDoctorByDoctorId(id);
        doctor.setEnable(true);
        doctorRepository.save(doctor);
    }

    public boolean checkIfAccountEnable(int id){
        Doctor doctor = doctorRepository.findDoctorByUserIdIfEnable(id).orElse(null);
        if(doctor != null){
            return true;
        }else {
            return false;
        }
    }


}
