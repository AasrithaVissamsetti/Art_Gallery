package com.project.controller;

import com.project.model.User;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")  // React frontend URL
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String response = userService.registerUser(user);
        return ResponseEntity.ok(response); 
    }

    // Endpoint to login user
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        String response = userService.loginUser(loginRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getUserProfile(@PathVariable String email) {
        System.out.println("Received request for user profile with email: " + email);
        User user = userService.getUserByEmail(email);

        if (user != null) {
            System.out.println("User found: " + user.toString());
            return ResponseEntity.ok(user);  // Return user details
        }
        System.out.println("User not found for email: " + email);
        return ResponseEntity.status(404).body(null);  // User not found
    }

}