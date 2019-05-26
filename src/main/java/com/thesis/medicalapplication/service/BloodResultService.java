package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.BloodResult;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.BloodResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodResultService {

    @Autowired
    UserService userService;

    @Autowired
    BloodResultRepository bloodResultRepository;


    public void saveResult(BloodResult bloodResult, String username) {
        User user = userService.findUserByUsername(username);
        bloodResult.setUser(user);
        bloodResultRepository.save(bloodResult);
    }

    public List<BloodResult> findResultsByUserId(int userId) {
        return bloodResultRepository.findBloodResultByUserId(userId);
    }

    public List<BloodResult> findResultsByUsername(String username) {
        return bloodResultRepository.findBloodResultByUserId(userService.findUserByUsername(username).getUserId());
    }

    public List<BloodResult> findBloodResultByParameter(String parameter, String username) {
        return bloodResultRepository.findBloodResultByParameter(parameter, userService.findUserByUsername(username).getUserId());
    }

    public List<String> findUnionParameters(String username) {
        return bloodResultRepository.findUnionParameters(userService.findUserByUsername(username).getUserId());
    }

    public void delete(int id) {
        bloodResultRepository.deleteById(id);
    }
}
