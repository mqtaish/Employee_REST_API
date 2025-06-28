package com.employeeManagmentSystem.employeeManagmentSystem.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateEmployee(

        @NotNull(message = "First Name is required")
        @JsonProperty("first_name")
        String firstName,
        @NotNull(message = "Last Name is required")
        @JsonProperty("last_name")
        String lastName,
        @NotBlank(message = "Phone Number can't be blank")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "invalid phone number format")
        @JsonProperty("phone_number")
        String phoneNumber


) {
}
