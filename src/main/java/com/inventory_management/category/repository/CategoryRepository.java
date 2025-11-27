package com.inventory_management.category.repository;

import com.inventory_management.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByOrgIdAndIsActive(Long orgId, Boolean isActive);
    List<Category> findByOrgId(Long orgId);
    Optional<Category> findByIdAndOrgId(Long id, Long orgId);
    boolean existsByNameAndOrgId(String name, Long orgId);
}

