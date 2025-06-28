package com.employeeManagmentSystem.employeeManagmentSystem.Repositories;

import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

    Employee findByfirstName(String username);
    Optional<Employee> findOneByAccountCreationToken(String token);



}
