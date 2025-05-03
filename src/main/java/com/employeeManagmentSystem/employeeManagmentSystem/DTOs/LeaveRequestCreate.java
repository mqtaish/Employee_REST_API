package com.employeeManagmentSystem.employeeManagmentSystem.DTOs;

import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

public record LeaveRequestCreate(
        @JsonProperty("reason")

        @NotNull(message = "Reason is required")
        @Size(min = 2, max = 100, message = "min is 2 characters and max is 100 characters")
        String reason,
        @JsonProperty("startDate")

        @FutureOrPresent(message = "Start Date should be now or in the future")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @FutureOrPresent(message = "Start Date should be now or in the future")
        @JsonProperty("endDate")

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate endDate


) {

}
