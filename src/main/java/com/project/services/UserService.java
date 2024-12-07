package com.project.services;

import com.project.model.User;
import com.project.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to register a new user
    @Transactional
    public String registerUser(User user) {
    	try {
        System.out.println("Registering user: " + user.getEmail());
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists!";
        }
        userRepository.save(user);  // Ensure save is called
        return "User registered successfully!";
    	}catch (Exception e) {
            e.printStackTrace();
            return "Error saving user: " + e.getMessage();
        }
    }

    // Method to login user
    public String loginUser(User loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            return "User not found!";
        }
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return "Invalid password!";
        }
        return "Login successful!";
    }
    // Method to get a user by email
    public User getUserByEmail(String email) {
        System.out.println("Looking for user with email: " + email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User not found with email: " + email);
        }
        return user;
    }

    // Method to update user details (name, etc.)
    public void updateUser(User user) {
        userRepository.save(user);  // Save the updated user to the database
    }
}

