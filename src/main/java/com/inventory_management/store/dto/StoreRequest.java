package com.inventory_management.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {

    @NotBlank(message = "Store name is required")
    @Size(max = 100, message = "Store name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Location type is required")
    private String locationType;  // RETAIL_STORE, WAREHOUSE, DISTRIBUTION_CENTER

    @Size(max = 100, message = "Manager name cannot exceed 100 characters")
    private String managerName;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String fullAddress;

    @Size(max = 15, message = "Phone number cannot exceed 15 characters")
    private String phoneNumber;

    @Email(message = "Please provide a valid email")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    private String notes;
}

