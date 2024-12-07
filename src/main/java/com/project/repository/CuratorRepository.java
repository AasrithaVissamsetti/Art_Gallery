package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Curator;

@Repository
public interface CuratorRepository extends JpaRepository<Curator, Long> {
    Curator findByName(String name); // To check if the curator with the given email already exists
}