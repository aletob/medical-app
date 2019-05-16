package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.GeneralResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralResultRepository extends JpaRepository<GeneralResult, Integer> {

    @Query(value = "Select * from general_result where result_id=:id", nativeQuery = true)
    GeneralResult findGeneralResultById(@Param("id") int id);

    @Query(value = "Select * from general_result where user_id=:userId", nativeQuery = true)
    List<GeneralResult> findGeneralResultByUserId(@Param("userId") int userId);
}
