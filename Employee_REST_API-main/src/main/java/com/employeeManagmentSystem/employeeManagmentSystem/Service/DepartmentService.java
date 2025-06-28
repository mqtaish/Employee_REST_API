package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.CreateDepartment;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Department;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;


public interface DepartmentService {
    Department findOne(UUID departmentId);
    List<Department> findAll();
    String createOne(@RequestBody CreateDepartment department);
    void deleteOne(UUID departmentId);

}
