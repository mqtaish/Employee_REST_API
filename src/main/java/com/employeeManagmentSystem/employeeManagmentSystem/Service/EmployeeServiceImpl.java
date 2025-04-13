package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.CustomizedExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.GlobalResponse;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service

public class EmployeeServiceImpl implements EmployeeService{

    private ArrayList<Employee> employeeArrayList = new ArrayList<>();

    @Override
    public void deleteEmployee(UUID employeeId) {
        System.out.println("Delete Mapping");
        Employee currentEmployee = employeeArrayList.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(()-> CustomizedExceptionHandler.ResourseNotFound("Resourse Not Found"));
        employeeArrayList.remove(currentEmployee);
    }

    @Override
    public String createEmployee(Employee employee) {
        if (employeeArrayList == null) {
            employeeArrayList = new ArrayList<>();
        }

        // Check if the employee already exists
        boolean exists = employeeArrayList.stream()
                .anyMatch(emp -> emp.getEmail().equals(employee.getEmail())); // Assuming email is unique

        if (exists) {
            return "Employee with this email already exists.";
        }

        // Generate unique ID (assuming Employee has a UUID field)
        employee.setId(UUID.randomUUID());


        employeeArrayList.add(employee);

        //employeeArrayList.forEach(em -> System.out.println(em.getEmail()));

        return "Employee Created Successfully";
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeArrayList.stream().filter(emp -> emp.getId().equals(employee.getId())).findFirst().
                orElse(null);
        //   orElseThrow(()->CustomizedExceptionHandler.ResourseNotFound("Employee with id"+ employee.getId() + "not Found"));

        if(updatedEmployee  != null) {
            updatedEmployee.setEmail(employee.getEmail());
            updatedEmployee.setFirst_name(employee.getFirst_name());
            updatedEmployee.setLast_name(employee.getLast_name());
            updatedEmployee.setPhone_number(employee.getPhone_number());
        }
        return updatedEmployee;
    }

    @Override
    public Employee getEmployeeById(UUID employeeId) {

        System.out.println("400 handler triggered");
        Employee currentEmployee = employeeArrayList.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(()-> CustomizedExceptionHandler.ResourseNotFound("Resourse Not Found"));
        return currentEmployee;
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        return employeeArrayList;
    }
}
