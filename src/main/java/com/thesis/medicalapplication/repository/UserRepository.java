package com.thesis.medicalapplication.repository;


import com.thesis.medicalapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query(value = "Select * from user where user_id=:userId", nativeQuery = true)
    User findUserById(@Param("userId") int userId);

}
