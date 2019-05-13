package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.Consultation;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.ConsultationRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    UserRepository userRepository;

    public List<Consultation> findAllUserConsultations(String username){
        User user = userRepository.findByUsername(username);
        return consultationRepository.findAllUserConsultation(user.getUserId());
    }

    public List<Consultation> findAllUserNotAnsweredUserConsultation(String username){
        User user = userRepository.findByUsername(username);
        return consultationRepository.findNotAnsweredUserConsultation(user.getUserId());
    }

    public Consultation findConsultationById(int id){
        return consultationRepository.findConsultationById(id);
    }

    public void saveConsultation(Consultation consultation, String username){
        User user = userRepository.findByUsername(username);
        consultation.setUser(user);
        consultationRepository.save(consultation);
    }

    public void delete(int id){
        consultationRepository.deleteById(id);
    }

}
