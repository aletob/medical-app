package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.MedicalVisit;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.MedicalVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalVisitService {

    @Autowired
    UserService userService;

    @Autowired
    MedicalVisitRepository medicalVisitRepository;

    public void saveVisit(MedicalVisit visit, String username){
        User user = userService.findUserByUsername(username);
        visit.setUser(user);
        medicalVisitRepository.save(visit);
    }

    public void delete(int id){
        medicalVisitRepository.deleteById(id);
    }

    public List<MedicalVisit> findAllUserVisits(String username){
        User user = userService.findUserByUsername(username);
        return medicalVisitRepository.findPatientVisits(user.getUserId());
    }

    public List<MedicalVisit> findFutureVisits(String username){
        User user = userService.findUserByUsername(username);
        return medicalVisitRepository.findPatientFutureVisits(user.getUserId());
    }

}
