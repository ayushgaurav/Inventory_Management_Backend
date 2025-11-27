package com.inventory_management.store.repository;

import com.inventory_management.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByOrgIdAndIsActive(Long orgId, Boolean isActive);
    List<Store> findByOrgId(Long orgId);
    Optional<Store> findByIdAndOrgId(Long id, Long orgId);
    boolean existsByNameAndOrgId(String name, Long orgId);
}

