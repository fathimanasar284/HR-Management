package com.hrmanagement.hrmanagement.controller;

import com.hrmanagement.hrmanagement.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.hrmanagement.hrmanagement.dao.UserRepository;
import com.hrmanagement.hrmanagement.dao.EmployeeRepository;
import com.hrmanagement.hrmanagement.model.User;
import com.hrmanagement.hrmanagement.model.Employee;

import java.security.MessageDigest;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Method to hash passwords using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Endpoint to register a new user
    @PostMapping("/register")
    public RedirectView register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String role,
                                 @RequestParam int employeeId) {

        // Fetch the employee from the database
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Create and populate the user
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword(password));
        user.setRole(Role.valueOf(role.toUpperCase()));
        user.setEmployee(employee); // Set employee entity

        // Save the user
        userRepository.save(user);

        // Redirect to login page
        return new RedirectView("/pages/login.html");
    }
}
