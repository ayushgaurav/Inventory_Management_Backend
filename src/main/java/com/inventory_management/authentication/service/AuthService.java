package com.inventory_management.authentication.service;

import com.inventory_management.authentication.dto.AuthResponse;
import com.inventory_management.authentication.dto.LoginRequest;
import com.inventory_management.authentication.dto.RegisterRequest;
import com.inventory_management.authentication.entity.Organization;
import com.inventory_management.authentication.entity.User;
import com.inventory_management.authentication.repository.OrganizationRepository;
import com.inventory_management.authentication.repository.UserRepository;
import com.inventory_management.authentication.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        if (request.getPhoneNumber() != null && userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already registered");
        }

        // Create or get organization
        Organization organization = organizationRepository.findByOrganizationName(request.getOrganizationName())
                .orElseGet(() -> {
                    Organization newOrganization = Organization.builder()
                            .organizationName(request.getOrganizationName())
                            .isActive(true)
                            .build();
                    return organizationRepository.save(newOrganization);
                });

        // Create new user
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .orgId(organization.getId())
                .role(User.Role.USER)
                .isActive(true)
                .verified(false)
                .build();

        User savedUser = userRepository.save(user);

        // Generate JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("orgId", savedUser.getOrgId());
        claims.put("role", savedUser.getRole().name());
        String token = jwtUtils.generateToken(savedUser.getEmail(), claims);

        return AuthResponse.builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .phoneNumber(savedUser.getPhoneNumber())
                .orgId(savedUser.getOrgId())
                .organizationName(organization.getOrganizationName())
                .verified(savedUser.getVerified())
                .role(savedUser.getRole().name())
                .token(token)
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Get user details
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get organization details
        Organization organization = organizationRepository.findById(user.getOrgId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        // Generate JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("orgId", user.getOrgId());
        claims.put("role", user.getRole().name());
        String token = jwtUtils.generateToken(user.getEmail(), claims);

        return AuthResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .orgId(user.getOrgId())
                .organizationName(organization.getOrganizationName())
                .verified(user.getVerified())
                .role(user.getRole().name())
                .token(token)
                .build();
    }

    public User getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

