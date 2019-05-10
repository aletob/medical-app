package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.MedicalVisit;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.MedicalVisitRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MedicalVisitService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MedicalVisitRepository medicalVisitRepository;

    public void saveVisit(MedicalVisit visit, String username){
        User user = userRepository.findByUsername(username);
        visit.setUser(user);
        medicalVisitRepository.save(visit);
    }

    public void delete(int id){
        medicalVisitRepository.deleteById(id);
    }

    public List<MedicalVisit> findAllUserVisits(String username){
        User user = userRepository.findByUsername(username);
        return medicalVisitRepository.findPatientVisits(user.getUserId());
    }

    public MedicalVisit findVisitById(int id){
        return medicalVisitRepository.findVisitById(id);
    }
}
