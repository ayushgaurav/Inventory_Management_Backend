package com.inventory_management.store.controller;

import com.inventory_management.common.response.ApiResponseWrapper;
import com.inventory_management.store.dto.StoreRequest;
import com.inventory_management.store.dto.StoreResponse;
import com.inventory_management.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<StoreResponse>> createStore(
            @Valid @RequestBody StoreRequest request,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            StoreResponse response = storeService.createStore(request, email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Store created successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to create store"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseWrapper<List<StoreResponse>>> getAllStores(
            Authentication authentication) {
        try {
            String email = authentication.getName();
            List<StoreResponse> response = storeService.getAllStores(email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Stores retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve stores"));
        }
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponseWrapper<List<StoreResponse>>> getActiveStores(
            Authentication authentication) {
        try {
            String email = authentication.getName();
            List<StoreResponse> response = storeService.getActiveStores(email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Active stores retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve active stores"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<StoreResponse>> getStoreById(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            StoreResponse response = storeService.getStoreById(id, email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Store retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve store"));
        }
    }
}


