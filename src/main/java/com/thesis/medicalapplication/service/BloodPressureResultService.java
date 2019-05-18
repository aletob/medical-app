package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.BloodPressureResult;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.BloodPressureResultRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodPressureResultService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BloodPressureResultRepository bloodPressureResultRepository;


    public void saveResult(BloodPressureResult bloodPressureResult, String username) {
        User user = userRepository.findByUsername(username);
        bloodPressureResult.setUser(user);
        bloodPressureResultRepository.save(bloodPressureResult);
    }

    public BloodPressureResult findResultById(int id) {
        return bloodPressureResultRepository.findBloodPressureResultById(id);
    }

    public List<BloodPressureResult> findResultsByUserId(int userId) {
        return bloodPressureResultRepository.findBloodPressureResultByUserId(userId);
    }

    public List<BloodPressureResult> findResultsByUsername(String username) {
        return bloodPressureResultRepository.findBloodPressureResultByUserId(userRepository.findByUsername(username).getUserId());
    }

    public List<BloodPressureResult> findAll() {
        return bloodPressureResultRepository.findAll();
    }

    public void delete(int id) {
        bloodPressureResultRepository.deleteById(id);
    }
}
