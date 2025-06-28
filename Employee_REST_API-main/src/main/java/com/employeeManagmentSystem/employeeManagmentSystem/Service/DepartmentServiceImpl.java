package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.CreateDepartment;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.CustomizedExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.DepartmentRepo;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Department;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Department findOne(UUID departmentId) {
        System.out.println("Fuck Debugging");
        Department currentDepartment = departmentRepo.findById(departmentId)
                .orElseThrow(() -> CustomizedExceptionHandler.ResourseNotFound("Resourse Not Found"));
        return currentDepartment;
    }

    @Override
    public List<Department> findAll() {
        System.out.println("Fuck Debugging");
        return departmentRepo.findAll();
    }

    @Override
    public String createOne(CreateDepartment department) {

        Department currentDepartment = new Department();
        currentDepartment.setName(department.name());

        departmentRepo.save(currentDepartment);
        return "Creation Done Successfully";
    }

    @Override
    public void deleteOne(UUID departmentId) {
        Department currentDepartment = departmentRepo.findById(departmentId)
                .orElseThrow(() -> CustomizedExceptionHandler.ResourseNotFound("Resourse Not Found"));
        departmentRepo.deleteById(departmentId);
    }
}
