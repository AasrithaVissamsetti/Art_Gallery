package com.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Curator;
import com.project.services.CuratorService;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/api/curators")
public class CuratorController {

    @Autowired
    private CuratorService curatorService;

    // Register a new curator
    @PostMapping("/register")
    public String registerCurator(@Validated @RequestBody  Curator curator, BindingResult bindingResult) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return String.join(", ", errorMessages);
        }

        // Register the curator
        curatorService.registerCurator(curator);
        return "Registration successful!";
 }
    @PostMapping("/login")
    public ResponseEntity<String> loginCurator(@RequestBody Curator loginRequest) {
        String response = curatorService.loginCurator(loginRequest);
        return ResponseEntity.ok(response); // Send the response back
    }
}
