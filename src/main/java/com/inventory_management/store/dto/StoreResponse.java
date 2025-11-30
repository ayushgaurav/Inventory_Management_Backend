package com.inventory_management.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {
    private Long id;
    private String name;
    private String locationType;
    private String managerName;
    private String fullAddress;
    private String phoneNumber;
    private String email;
    private String notes;
    private Long orgId;
    private Boolean isActive;
    private Long createdAt;
    private Long updatedAt;
}


