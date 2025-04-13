package com.employeeManagmentSystem.employeeManagmentSystem.Repositories;

import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

}
