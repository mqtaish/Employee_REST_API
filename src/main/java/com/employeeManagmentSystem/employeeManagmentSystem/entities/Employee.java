package com.employeeManagmentSystem.employeeManagmentSystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


public class Employee {
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull(message = "First Name is required")
    @JsonProperty("first_name")
    private String firstName;
    @NotNull(message = "Last Name is required")

    @JsonProperty("last_name")
    private String lastName;
    @NotNull(message = "email Name is required")
    @Email(message = "Email not in proper format")

    @JsonProperty("email")
    private String email;

    @NotBlank(message = "Phone Number can't be blank")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "invalid phone number format")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("hire_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    public Employee(UUID id, String first_name, String last_name, String email, String phone_number, LocalDate hire_date) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.phoneNumber = phone_number;
        this.hireDate = hire_date;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.firstName = first_name;
    }

    public void setLast_name(String last_name) {
        this.lastName = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hireDate = hire_date;
    }

    public UUID getId() {
        return id;
    }

    public String getFirst_name() {
        return firstName;
    }

    public String getLast_name() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phoneNumber;
    }

    public LocalDate getHire_date() {
        return hireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(email, employee.email) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(hireDate, employee.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, hireDate);
    }
}
