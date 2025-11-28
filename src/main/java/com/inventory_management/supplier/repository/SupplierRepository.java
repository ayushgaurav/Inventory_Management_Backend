package com.inventory_management.supplier.repository;

import com.inventory_management.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByOrgIdAndIsActive(Long orgId, Boolean isActive);
    List<Supplier> findByOrgId(Long orgId);
    Optional<Supplier> findByIdAndOrgId(Long id, Long orgId);
    boolean existsByCompanyNameAndOrgId(String companyName, Long orgId);
    boolean existsByEmailAndOrgId(String email, Long orgId);
}

