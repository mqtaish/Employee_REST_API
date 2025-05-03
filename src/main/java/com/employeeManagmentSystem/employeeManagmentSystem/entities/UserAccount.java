package com.employeeManagmentSystem.employeeManagmentSystem.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;
@Entity
@Table(name = "user-account")

public class UserAccount {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;
    @Column(name = "username", unique = true, nullable = false, length = 10)
    private String userName;
    @Column(name = "password", nullable = false, length = 100)

    private String password;
    @Column(name = "role", nullable = false)
    private String role = "USER";

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee-id", unique = true, nullable = false)
    private Employee employee;


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
