package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value = "Select * from doctor where doctor_id=:id", nativeQuery = true)
    Doctor findDoctorByDoctorId(@Param("id") int id);

    @Query(value = "Select * from doctor where user_id=:id", nativeQuery = true)
    Doctor findDoctorByUserId(@Param("id") int id);

    @Query(value = "Select * from doctor where specialization=:specialization", nativeQuery = true)
    List<Doctor> findDoctorsBySpecialization(@Param("specialization") String specialization);

    @Query(value = "Select * from doctor", nativeQuery = true)
    List<Doctor> findAllDoctors();

}
