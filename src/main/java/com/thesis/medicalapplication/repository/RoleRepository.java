package com.thesis.medicalapplication.repository;

import com.thesis.medicalapplication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
