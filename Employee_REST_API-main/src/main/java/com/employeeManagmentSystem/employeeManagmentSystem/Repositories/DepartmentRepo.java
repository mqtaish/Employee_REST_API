package com.employeeManagmentSystem.employeeManagmentSystem.Repositories;

import com.employeeManagmentSystem.employeeManagmentSystem.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, UUID> {


}
