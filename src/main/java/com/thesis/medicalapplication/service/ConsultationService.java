package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.Consultation;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.ConsultationRepository;
import com.thesis.medicalapplication.repository.DoctorRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

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
        if(consultation.getConsultationId() != 0){
            Consultation consultationDB = consultationRepository.findConsultationById(consultation.getConsultationId());
            consultationDB.setAnswer(consultation.getAnswer());
            consultationDB.setAnswerDate(new Date());
            consultationRepository.save(consultationDB);
        }else{
            User user = userRepository.findByUsername(username);
            consultation.setUser(user);
            Date date = new Date();
            consultation.setQuestionDate(date);
            consultationRepository.save(consultation);
        }
    }

    public void delete(int id){
        consultationRepository.deleteById(id);
    }

    public List<Consultation> findAllDoctorConsultations(String username){
        int userId = userRepository.findByUsername(username).getUserId();
        int doctorId = doctorRepository.findDoctorByUserId(userId).orElse(null).getDoctorId();
        return consultationRepository.findAllDoctorConsultation(doctorId);
    }

    public List<Consultation> findNotAnsweredDoctorConsultations(String username){
        int userId = userRepository.findByUsername(username).getUserId();
        int doctorId = doctorRepository.findDoctorByUserId(userId).orElse(null).getDoctorId();
        return consultationRepository.findNotAnsweredDoctorConsultation(doctorId);
    }

}
