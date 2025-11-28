package com.inventory_management.supplier.service;

import com.inventory_management.authentication.entity.User;
import com.inventory_management.authentication.repository.UserRepository;
import com.inventory_management.supplier.dto.SupplierRequest;
import com.inventory_management.supplier.dto.SupplierResponse;
import com.inventory_management.supplier.entity.Supplier;
import com.inventory_management.supplier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;

    @Transactional
    public SupplierResponse createSupplier(SupplierRequest request, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if supplier company name already exists for this organization
        if (supplierRepository.existsByCompanyNameAndOrgId(request.getCompanyName(), user.getOrgId())) {
            throw new RuntimeException("Supplier with this company name already exists in your organization");
        }

        // Check if email already exists for this organization
        if (supplierRepository.existsByEmailAndOrgId(request.getEmail(), user.getOrgId())) {
            throw new RuntimeException("Supplier with this email already exists in your organization");
        }

        // Create supplier
        Supplier supplier = Supplier.builder()
                .companyName(request.getCompanyName())
                .contactPerson(request.getContactPerson())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .paymentTerms(request.getPaymentTerms())
                .creditLimit(request.getCreditLimit() != null ? request.getCreditLimit() : BigDecimal.ZERO)
                .notes(request.getNotes())
                .totalPurchases(BigDecimal.ZERO)
                .creditUsed(BigDecimal.ZERO)
                .rating(BigDecimal.ZERO)
                .orgId(user.getOrgId())
                .isActive(true)
                .build();

        Supplier savedSupplier = supplierRepository.save(supplier);

        return mapToResponse(savedSupplier);
    }

    public List<SupplierResponse> getAllSuppliers(String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get all suppliers for user's organization
        List<Supplier> suppliers = supplierRepository.findByOrgId(user.getOrgId());

        return suppliers.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<SupplierResponse> getActiveSuppliers(String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get only active suppliers for user's organization
        List<Supplier> suppliers = supplierRepository.findByOrgIdAndIsActive(user.getOrgId(), true);

        return suppliers.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public SupplierResponse getSupplierById(Long supplierId, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get supplier and verify it belongs to user's organization
        Supplier supplier = supplierRepository.findByIdAndOrgId(supplierId, user.getOrgId())
                .orElseThrow(() -> new RuntimeException("Supplier not found or access denied"));

        return mapToResponse(supplier);
    }

    @Transactional
    public SupplierResponse updateSupplier(Long supplierId, SupplierRequest request, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get supplier and verify it belongs to user's organization
        Supplier supplier = supplierRepository.findByIdAndOrgId(supplierId, user.getOrgId())
                .orElseThrow(() -> new RuntimeException("Supplier not found or access denied"));

        // Check if new company name conflicts with existing supplier (excluding current one)
        if (!supplier.getCompanyName().equals(request.getCompanyName()) &&
            supplierRepository.existsByCompanyNameAndOrgId(request.getCompanyName(), user.getOrgId())) {
            throw new RuntimeException("Supplier with this company name already exists in your organization");
        }

        // Check if new email conflicts with existing supplier (excluding current one)
        if (!supplier.getEmail().equals(request.getEmail()) &&
            supplierRepository.existsByEmailAndOrgId(request.getEmail(), user.getOrgId())) {
            throw new RuntimeException("Supplier with this email already exists in your organization");
        }

        // Update supplier
        supplier.setCompanyName(request.getCompanyName());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());
        supplier.setPaymentTerms(request.getPaymentTerms());
        supplier.setCreditLimit(request.getCreditLimit() != null ? request.getCreditLimit() : BigDecimal.ZERO);
        supplier.setNotes(request.getNotes());

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return mapToResponse(updatedSupplier);
    }

    @Transactional
    public void deleteSupplier(Long supplierId, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get supplier and verify it belongs to user's organization
        Supplier supplier = supplierRepository.findByIdAndOrgId(supplierId, user.getOrgId())
                .orElseThrow(() -> new RuntimeException("Supplier not found or access denied"));

        // Soft delete
        supplier.setIsActive(false);
        supplierRepository.save(supplier);
    }

    private SupplierResponse mapToResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .id(supplier.getId())
                .companyName(supplier.getCompanyName())
                .contactPerson(supplier.getContactPerson())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .address(supplier.getAddress())
                .paymentTerms(supplier.getPaymentTerms())
                .creditLimit(supplier.getCreditLimit())
                .notes(supplier.getNotes())
                .totalPurchases(supplier.getTotalPurchases())
                .creditUsed(supplier.getCreditUsed())
                .rating(supplier.getRating())
                .orgId(supplier.getOrgId())
                .isActive(supplier.getIsActive())
                .createdAt(supplier.getCreatedAt())
                .updatedAt(supplier.getUpdatedAt())
                .build();
    }
}

