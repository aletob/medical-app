package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.Bug;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BugService {

    @Autowired
    BugRepository bugRepository;

    @Autowired
    UserService userService;

    public void saveBug(Bug bug, String username){
        User user = userService.findUserByUsername(username);
        Date date = new Date();
        bug.setReportedUser(user);
        bug.setReportDate(date);
        bug.setFixed(false);
        bugRepository.save(bug);
    }

    public List<Bug> findAllNotFixed(){
        return bugRepository.findBugsNotFixed();
    }

    public List<Bug> findAll(){
        return bugRepository.findAll();
    }

    public void changeStatus(int id){
        Bug bug = bugRepository.findById(id).orElse(null);
        if(bug != null){
            if(bug.isFixed()){
                bug.setFixed(false);
            }else{
                bug.setFixed(true);
            }
            bugRepository.save(bug);
        }
    }

}
