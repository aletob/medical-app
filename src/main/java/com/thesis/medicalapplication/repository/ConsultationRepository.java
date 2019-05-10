package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    @Query(value = "Select * from consultation where consultation_id=:id", nativeQuery = true)
    Consultation findConsultationById(@Param("id") int id);

    @Query(value = "Select * from consultation where doctor_id=:doctor_id", nativeQuery = true)
    List<Consultation> findAllDoctorConsultation(@Param("doctor_id") int doctor_id);

    @Query(value = "Select * from consultation where doctor_id=:doctor_id and answer_date is null", nativeQuery = true)
    List<Consultation> findNotAnsweredDoctorConsultation(@Param("doctor_id") int doctor_id);

    @Query(value = "Select * from consultation where user_id=:user_id", nativeQuery = true)
    List<Consultation> findAllUserConsultation(@Param("user_id") int user_id);

    @Query(value = "Select * from consultation where user_id=:user_id and answer_date is null", nativeQuery = true)
    List<Consultation> findNotAnsweredUserConsultation(@Param("user_id") int user_id);

    @Query(value = "Select * from consultation where user_id=:user_id and doctor_id=:doctor_id", nativeQuery = true)
    List<Consultation> finUserDoctorHistory(@Param("user_id") int user_id, @Param("doctor_id") int doctor_id);

}
