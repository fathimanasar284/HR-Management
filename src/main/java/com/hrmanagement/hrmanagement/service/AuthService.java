package com.hrmanagement.hrmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hrmanagement.hrmanagement.dao.UserRepository;
import com.hrmanagement.hrmanagement.dao.EmployeeRepository;
import com.hrmanagement.hrmanagement.model.*;
import com.hrmanagement.hrmanagement.util.PasswordUtil;


@Service
public class AuthService {

    private final UserRepository userRepo;
    private final EmployeeRepository empRepo;

    public AuthService(UserRepository userRepo, EmployeeRepository empRepo) {
        this.userRepo = userRepo;
        this.empRepo = empRepo;
    }

    @Transactional
    public boolean register(String username, String password, Role role, Integer employeeId) {
        if (userRepo.findByUsername(username) != null) return false;
        User u = new User();
        u.setUsername(username);
        u.setPassword(PasswordUtil.hash(password));
        u.setRole(role);
        if (employeeId != null) {
            empRepo.findById(employeeId).ifPresent(u::setEmployee);
        }
        userRepo.save(u);
        return true;
    }

    public User authenticate(String username, String password) {
       username = username.trim();
       password = password.trim();

       User u = userRepo.findByUsername(username);
       if (u == null) {
           System.out.println("AUTH FAIL: user not found: [" + username + "]");
           return null;
        }
        String hashedInput = PasswordUtil.hash(password);
        boolean match = hashedInput.equals(u.getPassword());

        System.out.println("AUTH DBG user=" + username);
        System.out.println("  input hash = " + hashedInput);
        System.out.println("  db    hash = " + u.getPassword());
        System.out.println("DB role = " + u.getRole());

        System.out.println("  equal? " + match + " (len in=" + hashedInput.length() + ", db=" + u.getPassword().length() + ")");

        return match ? u : null;
    }

}
