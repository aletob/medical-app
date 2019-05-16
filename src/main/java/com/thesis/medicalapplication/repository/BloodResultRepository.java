package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.BloodResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodResultRepository extends JpaRepository<BloodResult, Integer> {

    @Query(value = "Select * from blood_result where result_id=:id", nativeQuery = true)
    BloodResult findBloodResultById(@Param("id") int id);

    @Query(value = "Select * from blood_result where user_id=:userId", nativeQuery = true)
    List<BloodResult> findBloodResultByUserId(@Param("userId") int userId);

    @Query(value = "Select * from blood_result where parameter=:parameter and user_id=:userId", nativeQuery = true)
    List<BloodResult> findBloodResultByParameter(@Param("parameter") String parameter, @Param("userId") int userId);

}
