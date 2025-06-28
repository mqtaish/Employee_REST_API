package com.employeeManagmentSystem.employeeManagmentSystem.DTOs;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "Username is required")
        String  username,
        @NotNull(message = "Password is required")

        String password


) {
}
