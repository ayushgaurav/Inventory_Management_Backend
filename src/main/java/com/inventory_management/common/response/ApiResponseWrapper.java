package com.inventory_management.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseWrapper<T> {
    
    @Builder.Default
    private boolean success = true;
    
    private String message;
    
    private T data;
    
    private String error;
    
    @Builder.Default
    private long timestamp = Instant.now().toEpochMilli();
    
    private String path;
    
    // Success response methods
    public static <T> ApiResponseWrapper<T> success(T data) {
        return ApiResponseWrapper.<T>builder()
                .success(true)
                .data(data)
                .message("Operation completed successfully")
                .build();
    }
    
    public static <T> ApiResponseWrapper<T> success(T data, String message) {
        return ApiResponseWrapper.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .build();
    }
    
    public static <T> ApiResponseWrapper<T> success(String message) {
        return ApiResponseWrapper.<T>builder()
                .success(true)
                .message(message)
                .build();
    }
    
    // Error response methods
    public static <T> ApiResponseWrapper<T> error(String error) {
        return ApiResponseWrapper.<T>builder()
                .success(false)
                .error(error)
                .message("Operation failed")
                .build();
    }
    
    public static <T> ApiResponseWrapper<T> error(String error, String message) {
        return ApiResponseWrapper.<T>builder()
                .success(false)
                .error(error)
                .message(message)
                .build();
    }
    
    public static <T> ApiResponseWrapper<T> error(String error, String message, String path) {
        return ApiResponseWrapper.<T>builder()
                .success(false)
                .error(error)
                .message(message)
                .path(path)
                .build();
    }
    
    // Validation error response
    public static <T> ApiResponseWrapper<T> validationError(String error, String path) {
        return ApiResponseWrapper.<T>builder()
                .success(false)
                .error(error)
                .message("Validation failed")
                .path(path)
                .build();
    }
}

