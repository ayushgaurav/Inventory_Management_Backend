package com.inventory_management.supplier.controller;

import com.inventory_management.common.response.ApiResponseWrapper;
import com.inventory_management.supplier.dto.SupplierRequest;
import com.inventory_management.supplier.dto.SupplierResponse;
import com.inventory_management.supplier.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<SupplierResponse>> createSupplier(
            @Valid @RequestBody SupplierRequest request,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            SupplierResponse response = supplierService.createSupplier(request, email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Supplier created successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to create supplier"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseWrapper<List<SupplierResponse>>> getAllSuppliers(
            Authentication authentication) {
        try {
            String email = authentication.getName();
            List<SupplierResponse> response = supplierService.getAllSuppliers(email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Suppliers retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve suppliers"));
        }
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponseWrapper<List<SupplierResponse>>> getActiveSuppliers(
            Authentication authentication) {
        try {
            String email = authentication.getName();
            List<SupplierResponse> response = supplierService.getActiveSuppliers(email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Active suppliers retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve active suppliers"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<SupplierResponse>> getSupplierById(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            SupplierResponse response = supplierService.getSupplierById(id, email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Supplier retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve supplier"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<SupplierResponse>> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequest request,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            SupplierResponse response = supplierService.updateSupplier(id, request, email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Supplier updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to update supplier"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<String>> deleteSupplier(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            supplierService.deleteSupplier(id, email);
            return ResponseEntity.ok(ApiResponseWrapper.success("Supplier deleted successfully", "Supplier deleted"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to delete supplier"));
        }
    }
}


