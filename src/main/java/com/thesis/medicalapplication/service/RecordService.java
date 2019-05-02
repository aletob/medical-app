package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.Record;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.RecordRepository;
import com.thesis.medicalapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecordRepository recordRepository;


    public void saveRecord(Record record, String username){
        User user = userRepository.findByUsername(username);
        record.setUser(user);
        recordRepository.save(record);

    }

    public Record findRecordById(int id){
        return recordRepository.findRecordById(id);
    }

    public List<Record> findRecordsByUserId(int userId){return recordRepository.findRecordsByUserId(userId);}

    public List<Record> findRecordsByUsername(String username){
        return recordRepository.findRecordsByUserId(userRepository.findByUsername(username).getUserId());}

    public List<Record> findAll(){
        return recordRepository.findAll();
    }

    public void delete(int id){
        recordRepository.deleteById(id);
    }
}
