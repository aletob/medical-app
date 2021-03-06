package com.thesis.medicalapplication.service;

import com.thesis.medicalapplication.model.Medicine;
import com.thesis.medicalapplication.model.User;
import com.thesis.medicalapplication.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    UserService userService;

    @Autowired
    MedicineRepository medicineRepository;

    public void saveMedicine(Medicine medicine, String username){
        User user = userService.findUserByUsername(username);
        medicine.setUser(user);
        medicineRepository.save(medicine);
    }

    public void delete(int id){
        medicineRepository.deleteById(id);
    }

    public List<Medicine> findAllUserMedicines(String username){
        User user = userService.findUserByUsername(username);
        return medicineRepository.findMedicinesByUserId(user.getUserId());
    }

    public List<Medicine> findCurrentMedicines(String username){
        User user = userService.findUserByUsername(username);
        return medicineRepository.findCurrentMedicines(user.getUserId());
    }

}
