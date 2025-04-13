package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.CreateEmployee;
import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.UpdateEmployee;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.CustomizedExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.GlobalResponse;
import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.EmployeeRepo;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private ArrayList<Employee> employeeArrayList = new ArrayList<>();
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public void deleteEmployee(UUID employeeId) {
        System.out.println("Delete Mapping");
        Employee currentEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(()-> CustomizedExceptionHandler.ResourseNotFound("Resourse Not Found"));
        employeeRepo.delete(currentEmployee);
    }

    @Override
    public String createEmployee(CreateEmployee employeeCreateDTO) {
        if (employeeArrayList == null) {
            employeeArrayList = new ArrayList<>();
        }

        // Check if the employee already exists
        boolean exists = employeeArrayList.stream()
                .anyMatch(emp -> emp.getEmail().equals(employeeCreateDTO.email())); // Assuming email is unique

        if (exists) {
            return "Employee with this email already exists.";
        }

        // Generate unique ID (assuming Employee has a UUID field)
        Employee employee = new Employee();
      //  employee.setId(UUID.randomUUID());
        employee.setFirst_name(employeeCreateDTO.firstName());
        employee.setLast_name(employeeCreateDTO.lastName());
        employee.setPhone_number(employeeCreateDTO.phoneNumber());
        employee.setEmail(employeeCreateDTO.email());
        employee.setHire_date(employeeCreateDTO.hireDate());

        employeeRepo.save(employee);

        //employeeArrayList.forEach(em -> System.out.println(em.getEmail()));

        return "Employee Created Successfully";
    }

    @Override
    public Employee updateEmployee(UUID employeeId, UpdateEmployee updateEmployee) {
        Employee currentEmployee = employeeRepo.findById(employeeId).
                orElse(null);
        //   orElseThrow(()->CustomizedExceptionHandler.ResourseNotFound("Employee with id"+ employee.getId() + "not Found"));

        System.out.println(currentEmployee.getFirst_name());
        System.out.println(currentEmployee.getLast_name());
        System.out.println(currentEmployee.getId());
        System.out.println(currentEmployee.getPhone_number());
        System.out.println(currentEmployee.getHire_date());


        if(currentEmployee  != null) {
            currentEmployee.setFirst_name(updateEmployee.firstName());
            currentEmployee.setLast_name(updateEmployee.lastName());
            currentEmployee.setPhone_number(updateEmployee.phoneNumber());
        }
        employeeRepo.save(currentEmployee);
        return currentEmployee;
    }

    @Override
    public Employee getEmployeeById(UUID employeeId) {

        Employee currentEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(()-> CustomizedExceptionHandler.ResourseNotFound("Resourse Not Found"));
        return currentEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return  employeeRepo.findAll();

    }
}
