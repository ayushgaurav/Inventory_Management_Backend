package com.inventory_management.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {
    private Long id;
    private String companyName;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;
    private String paymentTerms;
    private BigDecimal creditLimit;
    private String notes;
    private BigDecimal totalPurchases;
    private BigDecimal creditUsed;
    private BigDecimal rating;
    private Long orgId;
    private Boolean isActive;
    private Long createdAt;
    private Long updatedAt;
}

