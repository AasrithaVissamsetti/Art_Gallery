package com.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.model.Curator;
import com.project.repository.CuratorRepository;

@Service
public class CuratorService {

    @Autowired
    private CuratorRepository curatorRepository;
    public Curator registerCurator(Curator curator) {
        // Check if curator with this email already exists
        if (curatorRepository.findByName(curator.getName()) != null) {
            throw new RuntimeException("Curator with this name already exists.");
        }
        // Save the curator to the database
        return curatorRepository.save(curator);
    }
    public String loginCurator(Curator loginRequest) {
        // Find curator by name
        Curator curator = curatorRepository.findByName(loginRequest.getName());
        if (curator == null) {
            return "Curator not found!";
        }

        // Compare plain-text passwords (without using password encoder)
        if (!curator.getPassword().equals(loginRequest.getPassword())) {
            return "Invalid password!";
        }

        return "Login successful!";
    }
}
