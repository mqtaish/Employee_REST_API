package com.employeeManagmentSystem.employeeManagmentSystem.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record SignupRequest(
        @NotNull(message = "Username is required")
        String username,
        @NotNull(message = "Password is required")

        String password,
        UUID employeeId
) {
}
