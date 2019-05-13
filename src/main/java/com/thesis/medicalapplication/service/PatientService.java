package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.Patient;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.PatientRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void savePatient(Patient patient, String username){
        User user = userRepository.findByUsername(username);
        patient.setUser(user);
        patientRepository.save(patient);
    }

    public Patient getPatientByUsername(String username){
        return userRepository.findByUsername(username).getPatient();
    }


}
