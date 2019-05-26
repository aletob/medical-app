package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.GeneralResult;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.GeneralResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralResultService {

    @Autowired
    UserService userService;

    @Autowired
    GeneralResultRepository generalResultRepository;


    public void saveResult(GeneralResult generalResult, String username) {
        User user = userService.findUserByUsername(username);
        generalResult.setUser(user);
        generalResultRepository.save(generalResult);
    }

    public List<GeneralResult> findResultsByUsername(String username) {
        return generalResultRepository.findGeneralResultByUserId(userService.findUserByUsername(username).getUserId());
    }

    public void delete(int id) {
        generalResultRepository.deleteById(id);
    }
}
