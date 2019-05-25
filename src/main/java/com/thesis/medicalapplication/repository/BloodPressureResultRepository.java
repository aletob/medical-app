package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.BloodPressureResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodPressureResultRepository extends JpaRepository<BloodPressureResult, Integer> {

    @Query(value = "Select * from blood_pressure_result where result_id=:id", nativeQuery = true)
    BloodPressureResult findBloodPressureResultById(@Param("id") int id);

    @Query(value = "Select * from blood_pressure_result where user_id=:userId", nativeQuery = true)
    List<BloodPressureResult> findBloodPressureResultByUserId(@Param("userId") int userId);

    @Query(value = "Select * from blood_pressure_result where user_id=:userId and date is not null", nativeQuery = true)
    List<BloodPressureResult> findBloodPressureResultWithDate(@Param("userId") int userId);
}
