package com.employeeManagmentSystem.employeeManagmentSystem.Repositories;

import com.employeeManagmentSystem.employeeManagmentSystem.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, UUID> {

}
