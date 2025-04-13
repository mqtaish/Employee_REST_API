package com.employeeManagmentSystem.employeeManagmentSystem.Controller;

import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.CustomizedExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.GlobalExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.GlobalResponse;
import com.employeeManagmentSystem.employeeManagmentSystem.Service.EmployeeService;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@Validated
public class EmployeeController {
    private final EmployeeService  employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService =employeeService;
    }
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable  UUID employeeId){
        System.out.println("Delete Mapping");
         employeeService.deleteEmployee(employeeId);
    }


    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody @Valid Employee employee) {
        System.out.println("test test test");

        var result  = employeeService.createEmployee(employee);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }


    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee ){
        var updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> getEmployeeById(@PathVariable  UUID employeeId){
           var currentEmployee  =  employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(currentEmployee),HttpStatus.OK);

    }

    @GetMapping("/employees")
    public ResponseEntity<ArrayList<Employee>> getAllEmployees() {
       var allEmployees = employeeService.getAllEmployees();
        allEmployees.forEach(employee -> System.out.println(employee.getEmail()));
        return new ResponseEntity<ArrayList<Employee>>(allEmployees,HttpStatus.OK);
    }
}
