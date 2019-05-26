package com.thesis.medicalapplication.service;


import com.thesis.medicalapplication.model.Doctor;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    UserService userService;

    @Autowired
    DoctorRepository doctorRepository;

    public void saveDoctor(Doctor doctor, String username){
        Doctor doctorDB = doctorRepository.findDoctorByUserId(userService.findUserByUsername(username).getUserId()).orElse(null);
        if(doctorDB != null){
            doctorDB.setName(doctor.getName());
            doctorDB.setSecondName(doctor.getSecondName());
            doctorDB.setAddress(doctor.getAddress());
            doctorDB.setHospital(doctor.getHospital());
            doctorDB.setSpecialization(doctor.getSpecialization());
            doctorRepository.save(doctorDB);

        }else {
            User user = userService.findUserByUsername(username);
            doctor.setUser(user);
            doctor.setEnable(false);
            doctorRepository.save(doctor);
        }
    }

    public Doctor getDoctorById(int id){
        return doctorRepository.findDoctorByDoctorId(id);
    }

    public Doctor getDoctorByUserId(int id){return doctorRepository.findDoctorByUserId(id).orElse(null);
    }

    public Doctor getDoctorByUsername(String username){
        return userService.findUserByUsername(username).getDoctor();
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

    public boolean checkIfAccountEnable(String username){
        int id = userService.findUserByUsername(username).getUserId();
        Doctor doctor = doctorRepository.findDoctorByUserIdIfEnable(id).orElse(null);
        if(doctor != null){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkIfDataFill(String username){
        int id = userService.findUserByUsername(username).getUserId();
        Doctor doctor = doctorRepository.findDoctorByUserId(id).orElse(null);
        if(doctor != null){
            return true;
        }else {
            return false;
        }
    }


}
