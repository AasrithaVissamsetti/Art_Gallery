package com.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Admin;
import com.project.repository.AdminRepository;


@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin registerAdmin(Admin admin) {
        if (adminRepository.existsByName(admin.getName())) {
            throw new IllegalArgumentException("Email already in use");
        }

        return adminRepository.save(admin);
    }
    public String login(String username, String password) {
        boolean admin = adminRepository.existsByName(username);

        return "Login successful!";
    }
}