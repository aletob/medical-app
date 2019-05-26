package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "Select * from patient where user_id=:id", nativeQuery = true)
    Patient findPatientByUserId(@Param("id") int id);
}
