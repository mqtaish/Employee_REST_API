package com.employeeManagmentSystem.employeeManagmentSystem.Controller;

import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.CreateEmployee;
import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.LeaveRequestCreate;
import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.UpdateEmployee;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.CustomizedExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.GlobalExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.GlobalResponse;
import com.employeeManagmentSystem.employeeManagmentSystem.Service.EmployeeService;
import com.employeeManagmentSystem.employeeManagmentSystem.Service.LeaveRequestService;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.LeaveRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@Validated
public class EmployeeController {
    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private  LeaveRequestService leaveRequestService;

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody @Valid CreateEmployee employee) {
        System.out.println("here it's create employee 1");

        var result = employeeService.createEmployee(employee);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable UUID employeeId, @RequestBody @Valid UpdateEmployee employee) {
        var updatedEmployee = employeeService.updateEmployee(employeeId, employee);
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> getEmployeeById(@PathVariable UUID employeeId) {
        var currentEmployee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(currentEmployee), HttpStatus.OK);

    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        System.out.println("Create employee endpoint hit");

        var allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<List<Employee>>(allEmployees, HttpStatus.OK);
    }


    @PostMapping("/employees/{employeeId}/leave-request")
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody @Valid LeaveRequestCreate leaveRequestCreate,
                                                           @PathVariable UUID employeeId) {

        var leaveRequest = leaveRequestService.createOne(leaveRequestCreate, employeeId);

        return new ResponseEntity<LeaveRequest>(leaveRequest, HttpStatus.OK);

    }

    @GetMapping("/employees/{employeeId}/leave-request")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequest(@PathVariable UUID employeeId) {
        List<LeaveRequest> employeeLeaveRequest = leaveRequestService.findAllByEmployeeID(employeeId);

        return new ResponseEntity<List<LeaveRequest>>(employeeLeaveRequest, HttpStatus.OK);

    }

}
