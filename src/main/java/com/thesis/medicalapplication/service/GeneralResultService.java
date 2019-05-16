package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.GeneralResult;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.GeneralResultRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralResultService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GeneralResultRepository generalResultRepository;


    public void saveResult(GeneralResult generalResult, String username){
        User user = userRepository.findByUsername(username);
        generalResult.setUser(user);
        generalResultRepository.save(generalResult);
    }

    public GeneralResult findResultById(int id){
        return generalResultRepository.findGeneralResultById(id);
    }

    public List<GeneralResult> findResultsByUserId(int userId){
        return generalResultRepository.findGeneralResultByUserId(userId);
    }

    public List<GeneralResult> findAll(){
        return generalResultRepository.findAll();
    }

    public void delete(int id){
        generalResultRepository.deleteById(id);
    }
}
