package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.Patient;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserService userService;

    @Transactional
    public void savePatient(Patient patient, String username){
        if(getPatientByUsername(username) != null){
            int userId = userService.findUserByUsername(username).getUserId();
            Patient patientDB =  patientRepository.findPatientByUserId(userId);
            patientDB.setName(patient.getName());
            patientDB.setSecondName(patient.getSecondName());
            patientDB.setAddress(patient.getAddress());
            patientDB.setAge(patient.getAge());
            patientDB.setBloodType(patient.getBloodType());
            patientDB.setHeight(patient.getHeight());
            patientDB.setWeight(patient.getWeight());
            patientRepository.save(patientDB);
        }else {
            User user = userService.findUserByUsername(username);
            patient.setUser(user);
            patientRepository.save(patient);
        }
    }

    public Patient getPatientByUsername(String username){
        return userService.findUserByUsername(username).getPatient();
    }

    public Patient getPatientByUserId(int userId){
        return patientRepository.findPatientByUserId(userId);
    }


}
