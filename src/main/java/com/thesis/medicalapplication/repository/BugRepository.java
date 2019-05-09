package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<Bug, Integer> {

    @Query(value = "Select * from bug where fixed=0", nativeQuery = true)
    List<Bug> findBugsNotFixed();
}
