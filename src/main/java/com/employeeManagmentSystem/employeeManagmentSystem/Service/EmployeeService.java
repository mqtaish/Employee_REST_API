package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.GlobalResponse;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.UUID;

public interface EmployeeService {


    public void deleteEmployee(@PathVariable UUID employeeId);

    public String createEmployee(@RequestBody @Valid Employee employee);
    public Employee updateEmployee(@RequestBody @Valid Employee employee );

    public Employee getEmployeeById(@PathVariable  UUID employeeId);

    public ArrayList<Employee> getAllEmployees() ;



}
