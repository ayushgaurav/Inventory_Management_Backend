package com.inventory_management.store.service;

import com.inventory_management.authentication.entity.User;
import com.inventory_management.authentication.repository.UserRepository;
import com.inventory_management.store.dto.StoreRequest;
import com.inventory_management.store.dto.StoreResponse;
import com.inventory_management.store.entity.Store;
import com.inventory_management.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public StoreResponse createStore(StoreRequest request, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if store name already exists for this organization
        if (storeRepository.existsByNameAndOrgId(request.getName(), user.getOrgId())) {
            throw new RuntimeException("Store with this name already exists in your organization");
        }

        // Create store
        Store store = Store.builder()
                .name(request.getName())
                .locationType(Store.LocationType.valueOf(request.getLocationType()))
                .managerName(request.getManagerName())
                .fullAddress(request.getFullAddress())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .notes(request.getNotes())
                .orgId(user.getOrgId())
                .isActive(true)
                .build();

        Store savedStore = storeRepository.save(store);

        return mapToResponse(savedStore);
    }

    public List<StoreResponse> getAllStores(String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get all stores for user's organization
        List<Store> stores = storeRepository.findByOrgId(user.getOrgId());

        return stores.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<StoreResponse> getActiveStores(String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get only active stores for user's organization
        List<Store> stores = storeRepository.findByOrgIdAndIsActive(user.getOrgId(), true);

        return stores.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public StoreResponse getStoreById(Long storeId, String userEmail) {
        // Get user from email (extracted from JWT)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get store and verify it belongs to user's organization
        Store store = storeRepository.findByIdAndOrgId(storeId, user.getOrgId())
                .orElseThrow(() -> new RuntimeException("Store not found or access denied"));

        return mapToResponse(store);
    }

    private StoreResponse mapToResponse(Store store) {
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .locationType(store.getLocationType().name())
                .managerName(store.getManagerName())
                .fullAddress(store.getFullAddress())
                .phoneNumber(store.getPhoneNumber())
                .email(store.getEmail())
                .notes(store.getNotes())
                .orgId(store.getOrgId())
                .isActive(store.getIsActive())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .build();
    }
}


