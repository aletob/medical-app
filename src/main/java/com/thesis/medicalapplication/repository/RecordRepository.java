package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Query(value = "Select * from record where record_id=:id", nativeQuery = true)
    Record findRecordById(@Param("id") int id);

    @Query(value = "Select * from record where user_id=:userId", nativeQuery = true)
    List<Record> findRecordsByUserId(@Param("userId") int userId);

    @Query(value = "Select * from record where parameter=:parameter and user_id=:userId", nativeQuery = true)
    List<Record> findRecordByParameter(@Param("parameter") String parameter, @Param("userId") int userId);

    //wyszukiwanie po dacie
}
