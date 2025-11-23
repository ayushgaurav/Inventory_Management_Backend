package com.inventory_management.authentication.controller;

import com.inventory_management.authentication.dto.AuthResponse;
import com.inventory_management.authentication.dto.LoginRequest;
import com.inventory_management.authentication.dto.RegisterRequest;
import com.inventory_management.authentication.entity.User;
import com.inventory_management.authentication.service.AuthService;
import com.inventory_management.common.response.ApiResponseWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseWrapper<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "User registered successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Registration failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseWrapper<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Login successful"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Invalid email or password"));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponseWrapper<User>> getCurrentUser(Authentication authentication) {
        try {
            String email = authentication.getName();
            User user = authService.getCurrentUser(email);
            // Remove password from response
            user.setPassword(null);
            return ResponseEntity.ok(ApiResponseWrapper.success(user, "User details retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve user details"));
        }
    }
}

