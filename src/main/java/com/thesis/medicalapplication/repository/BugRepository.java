package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, Integer> {
}
