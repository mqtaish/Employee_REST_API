package com.employeeManagmentSystem.employeeManagmentSystem.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record CreateDepartment(
        @NotNull(message = "Name is required")
        @JsonProperty("name")
        String name

)
{ }
