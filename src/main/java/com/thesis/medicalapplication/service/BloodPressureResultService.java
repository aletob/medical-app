package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.BloodPressureResult;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.BloodPressureResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodPressureResultService {

    @Autowired
    UserService userService;

    @Autowired
    BloodPressureResultRepository bloodPressureResultRepository;


    public void saveResult(BloodPressureResult bloodPressureResult, String username) {
        User user = userService.findUserByUsername(username);
        bloodPressureResult.setUser(user);
        bloodPressureResultRepository.save(bloodPressureResult);
    }


    public List<BloodPressureResult> findResultsByUserId(int userId) {
        return bloodPressureResultRepository.findBloodPressureResultByUserId(userId);
    }

    public List<BloodPressureResult> findResultsWithDate(String username) {
        return bloodPressureResultRepository.findBloodPressureResultWithDate(userService.findUserByUsername(username).getUserId());
    }

    public List<BloodPressureResult> findResultsByUsername(String username) {
        return bloodPressureResultRepository.findBloodPressureResultByUserId(userService.findUserByUsername(username).getUserId());
    }

    public void delete(int id) {
        bloodPressureResultRepository.deleteById(id);
    }
}
