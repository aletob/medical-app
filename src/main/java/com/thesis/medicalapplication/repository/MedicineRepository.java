package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

    @Query(value = "Select * from medicine where medicine_id=:id", nativeQuery = true)
    Medicine findMedicineById(@Param("id") int id);

    @Query(value = "Select * from medicine where user_id=:userId", nativeQuery = true)
    List<Medicine> findMedicinesByUserId(@Param("userId") int userId);

    @Query(value = "Select * from medicine where user_id=:userId and end_date is null", nativeQuery = true)
    List<Medicine> findCurrentMedicines(@Param("userId") int userId);
}
