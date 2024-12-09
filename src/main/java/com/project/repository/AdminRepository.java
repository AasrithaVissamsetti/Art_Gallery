package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	boolean existsByName(String name); // Check if admin exists by name

    Admin findByName(String name); 
}