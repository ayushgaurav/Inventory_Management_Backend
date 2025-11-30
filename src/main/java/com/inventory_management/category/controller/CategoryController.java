package com.inventory_management.category.controller;

import com.inventory_management.category.dto.CategoryRequest;
import com.inventory_management.category.dto.CategoryResponse;
import com.inventory_management.category.service.CategoryService;
import com.inventory_management.common.response.ApiResponseWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest request,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            CategoryResponse response = categoryService.createCategory(request, email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Category created successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to create category"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseWrapper<List<CategoryResponse>>> getAllCategories(
            Authentication authentication) {
        try {
            String email = authentication.getName();
            List<CategoryResponse> response = categoryService.getAllCategories(email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Categories retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve categories"));
        }
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponseWrapper<List<CategoryResponse>>> getActiveCategories(
            Authentication authentication) {
        try {
            String email = authentication.getName();
            List<CategoryResponse> response = categoryService.getActiveCategories(email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Active categories retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve active categories"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<CategoryResponse>> getCategoryById(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            CategoryResponse response = categoryService.getCategoryById(id, email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Category retrieved successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to retrieve category"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<CategoryResponse>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            CategoryResponse response = categoryService.updateCategory(id, request, email);
            return ResponseEntity.ok(ApiResponseWrapper.success(response, "Category updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to update category"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<String>> deleteCategory(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String email = authentication.getName();
            categoryService.deleteCategory(id, email);
            return ResponseEntity.ok(ApiResponseWrapper.success("Category deleted successfully", "Category deleted"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponseWrapper.error(e.getMessage(), "Failed to delete category"));
        }
    }
}


