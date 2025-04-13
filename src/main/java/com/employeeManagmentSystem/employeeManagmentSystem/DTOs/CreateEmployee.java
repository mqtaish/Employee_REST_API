package com.employeeManagmentSystem.employeeManagmentSystem.DTOs;

import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


public record CreateEmployee (

    @NotNull(message = "First Name is required")
    @JsonProperty("first_name")
     String firstName,
    @NotNull(message = "Last Name is required")

    @JsonProperty("last_name")
     String lastName,
    @NotNull(message = "email Name is required")
    @Email(message = "Email not in proper format")

    @JsonProperty("email")
     String email,

    @NotBlank(message = "Phone Number can't be blank")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "invalid phone number format")
    @JsonProperty("phone_number")
     String phoneNumber,

    @JsonProperty("hire_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     LocalDate hireDate

)
{}