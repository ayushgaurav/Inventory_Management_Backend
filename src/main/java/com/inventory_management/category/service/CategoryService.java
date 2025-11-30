package com.inventory_management.category.service;

import com.inventory_management.authentication.entity.User;
import com.inventory_management.authentication.repository.UserRepository;
import com.inventory_management.category.dto.CategoryRequest;
import com.inventory_management.category.dto.CategoryResponse;
import com.inventory_management.category.entity.Category;
import com.inventory_management.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public CategoryResponse createCategory(CategoryRequest request, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if category name already exists for this organization
        if (categoryRepository.existsByNameAndOrgId(request.getName(), user.getOrgId())) {
            throw new RuntimeException("Category with this name already exists in your organization");
        }

        // Create category
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .orgId(user.getOrgId())
                .isActive(true)
                .build();

        Category savedCategory = categoryRepository.save(category);

        return mapToResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategories(String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get all categories for user's organization
        List<Category> categories = categoryRepository.findByOrgId(user.getOrgId());

        return categories.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getActiveCategories(String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get only active categories for user's organization
        List<Category> categories = categoryRepository.findByOrgIdAndIsActive(user.getOrgId(), true);

        return categories.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long categoryId, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get category and verify it belongs to user's organization
        Category category = categoryRepository.findByIdAndOrgId(categoryId, user.getOrgId())
                .orElseThrow(() -> new RuntimeException("Category not found or access denied"));

        return mapToResponse(category);
    }

    @Transactional
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get category and verify it belongs to user's organization
        Category category = categoryRepository.findByIdAndOrgId(categoryId, user.getOrgId())
                .orElseThrow(() -> new RuntimeException("Category not found or access denied"));

        // Check if new name conflicts with existing category (excluding current one)
        if (!category.getName().equals(request.getName()) &&
            categoryRepository.existsByNameAndOrgId(request.getName(), user.getOrgId())) {
            throw new RuntimeException("Category with this name already exists in your organization");
        }

        // Update category
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return mapToResponse(updatedCategory);
    }

    @Transactional
    public void deleteCategory(Long categoryId, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get category and verify it belongs to user's organization
        Category category = categoryRepository.findByIdAndOrgId(categoryId, user.getOrgId())
                .orElseThrow(() -> new RuntimeException("Category not found or access denied"));

        // Soft delete
        category.setIsActive(false);
        categoryRepository.save(category);
    }

    private CategoryResponse mapToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .orgId(category.getOrgId())
                .isActive(category.getIsActive())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}


