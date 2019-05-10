package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.MedicalVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalVisitRepository extends JpaRepository<MedicalVisit, Integer> {

    @Query(value = "Select * from medical_visit where visit_id=:id", nativeQuery = true)
    MedicalVisit findVisitById(@Param("id") int id);

    @Query(value = "Select * from medical_visit where user_id=:userId", nativeQuery = true)
    List<MedicalVisit> findPatientVisits(@Param("userId") int userId);

    @Query(value = "Select * from medical_visit where specialization=:specialization and user_id=:userId", nativeQuery = true)
    List<MedicalVisit> findVisitBySpecialization(@Param("specialization") String specialization, @Param("userId") int userId);

    //wyszukiwanie po preszlosci / teraznieszosci
}
