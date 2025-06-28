package com.employeeManagmentSystem.employeeManagmentSystem.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "user-account")
public class UserAccount implements UserDetails{

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;
    @Column(name = "username", unique = true, nullable = false, length = 10)
    private String username;
    @Column(name = "password", nullable = false, length = 100)

    private String password;
    @Column(name = "role", nullable = false)
    private String role = "USER";

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee-id", unique = true, nullable = false)
    private Employee employee;


    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setUserName(String userName) {
        this.username = userName;
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
