package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.MedicalVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalVisitRepository extends JpaRepository<MedicalVisit, Integer> {
}
