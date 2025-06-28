package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.LeaveRequestCreate;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.CustomizedExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.EmployeeRepo;
import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.LeaveRequestRepo;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class LeaveRequestImpl implements LeaveRequestService{
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private LeaveRequestRepo leaveRequestRepo;
    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    @Override
    public LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate, UUID employeeId) {

        Employee currentEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(()-> CustomizedExceptionHandler.ResourseNotFound("Resourse Not Found"));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStatus("PENDING");
        leaveRequest.setStartDate(leaveRequestCreate.startDate());
        leaveRequest.setEndDate(leaveRequestCreate.endDate());
        leaveRequest.setReason(leaveRequestCreate.reason());
        leaveRequest.setEmployee(currentEmployee);

        leaveRequestRepo.save(leaveRequest);

        return leaveRequest;

    }
    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    @Override
    public List<LeaveRequest> findAllByEmployeeID(UUID employeeId) {
        return leaveRequestRepo.findAll();
    }
}
