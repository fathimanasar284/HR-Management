package com.hrmanagement.hrmanagement.controller;

import com.hrmanagement.hrmanagement.model.Role;
import com.hrmanagement.hrmanagement.model.User;
import com.hrmanagement.hrmanagement.service.AuthService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Authentication + registration endpoints.
 *
 * /api/auth/register  (form POST) -> redirect to login.html or back w/error
 * /api/auth/login     (form POST or fetch) -> JSON response {success, username, role, employeeId}
 */
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Constructor injection (Spring will auto-wire)
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Handle registration from an HTML form (application/x-www-form-urlencoded).
     * Redirects on success/failure.
     */
    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public RedirectView register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role,                 // accept as string; convert below
            @RequestParam(required = false) Integer employeeId
    ) {
        Role parsedRole;
        try {
            parsedRole = Role.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            // invalid role -> back to register page with error
            return new RedirectView("/pages/register.html?error=badrole");
        }

        boolean ok = authService.register(username, password, parsedRole, employeeId);
        return new RedirectView(ok
                ? "/pages/login.html?registered=1"
                : "/pages/register.html?error=exists");
    }

    /**
     * Handle login from HTML form or JS fetch (x-www-form-urlencoded).
     * Returns JSON response body.
     */
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    public LoginResponse login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        User u = authService.authenticate(username, password);
        if (u == null) {
            return new LoginResponse(false, null, null, null);
        }
        Integer empId = (u.getEmployee() != null) ? u.getEmployee().getId() : null;
        return new LoginResponse(true, u.getUsername(), u.getRole().name(), empId);
    }

    /**
     * Simple DTO for login responses.
     * (You can move this to its own file later if you prefer.)
     */
    public record LoginResponse(boolean success, String username, String role, Integer employeeId) {}
}
