package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.BloodResult;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.BloodResultRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodResultService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BloodResultRepository bloodResultRepository;


    public void saveResult(BloodResult bloodResult, String username){
        User user = userRepository.findByUsername(username);
        bloodResult.setUser(user);
        bloodResultRepository.save(bloodResult);

    }

    public BloodResult findResultById(int id){
        return bloodResultRepository.findBloodResultById(id);
    }

    public List<BloodResult> findResultsByUserId(int userId){
        return bloodResultRepository.findBloodResultByUserId(userId);
    }

    public List<BloodResult> findResultsByUsername(String username){
        return bloodResultRepository.findBloodResultByUserId(userRepository.findByUsername(username).getUserId());
    }

    public List<BloodResult> findBloodResultByParameter(String parameter, String username){
        return bloodResultRepository.findBloodResultByParameter(parameter, userRepository.findByUsername(username).getUserId());
    }

    public List<String> findUnionParameters(String username){
        return bloodResultRepository.findUnionParameters(userRepository.findByUsername(username).getUserId());
    }

    public List<BloodResult> findAll(){
        return bloodResultRepository.findAll();
    }

    public void delete(int id){
        bloodResultRepository.deleteById(id);
    }
}
