package com.thesis.medicalapplication.repository;


import com.thesis.medicalapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query(value = "Select * from user where user_id=:userId", nativeQuery = true)
    User findUserById(@Param("userId") int userId);

    @Query(value = "Select * from user where user_id in (select distinct(user_id) from consultation where doctor_id=:doctor_id)", nativeQuery = true)
    List<User> findUserForDoctor(@Param("doctor_id") int doctor_id);

}
