package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.LeaveRequestCreate;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.LeaveRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface LeaveRequestService {

LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate,  UUID employeeId);
List<LeaveRequest> findAllByEmployeeID(UUID employeeId);

}
