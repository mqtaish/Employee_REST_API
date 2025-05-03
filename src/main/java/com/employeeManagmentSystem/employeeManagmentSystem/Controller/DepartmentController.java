package com.employeeManagmentSystem.employeeManagmentSystem.Controller;

import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.CreateDepartment;
import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.CreateEmployee;
import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.UpdateEmployee;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.GlobalResponse;
import com.employeeManagmentSystem.employeeManagmentSystem.Service.DepartmentService;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Department;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> getDepartmentById(@PathVariable UUID employeeId) {
        var currentDepartment = departmentService.findOne(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(currentDepartment), HttpStatus.OK);

    }

    @GetMapping("/departments")
    public ResponseEntity<GlobalResponse<List<Department>>> getAllDepartment() {
        var allDepartment = departmentService.findAll();
        return new ResponseEntity<GlobalResponse<List<Department>>>(new GlobalResponse<List<Department>>(allDepartment), HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<String> createDepartment(@RequestBody @Valid CreateDepartment department) {

        var result  = departmentService.createOne(department);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

}
