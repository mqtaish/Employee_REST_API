package com.employeeManagmentSystem.employeeManagmentSystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name= "Employee")
public class Employee {
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 100)

    private String lastName;
    @Column(name = "email", nullable = false, unique = true)

    private String email;
    @Column(name = "phone_number",  length =25)

    private String phoneNumber;
    @Column(name = "hire_date", nullable = false)

    private LocalDate hireDate;

    @Column(name = "is_verified", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)

    private boolean isVerified;

    @Column(name = "account_creation_token")

    private String accountCreationToken;

    @ManyToOne(fetch= FetchType.EAGER, optional = false )
    @JoinColumn(name = "department_id", nullable = false)
    @JsonProperty("department_id")
    private Department department;



    public Employee(){}
    public Employee(UUID id, String first_name, String last_name, String email, String phone_number, LocalDate hire_date) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.phoneNumber = phone_number;
        this.hireDate = hire_date;
    }


    public boolean isVerified() {
        return isVerified;
    }

    public String getAccountCreationToken() {
        return accountCreationToken;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public void setAccountCreationToken(String accountCreationToken) {
        this.accountCreationToken = accountCreationToken;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
