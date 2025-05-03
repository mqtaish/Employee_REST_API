package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.LoginRequest;
import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.SignupRequest;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.CustomizedExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.EmployeeRepo;
import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.UserAccountRepo;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserAccountRepo userAccountRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String login(LoginRequest loginRequest){
        return "";
    };
    public void signup(SignupRequest signupRequest){
        Employee currentEmployee = employeeRepo.findById(signupRequest.employeeId())
                .orElseThrow(()-> CustomizedExceptionHandler.ResourseNotFound("Resourse Not Found"));

        System.out.println("we are register a new account in database");
        UserAccount account = new UserAccount();
        account.setUserName(signupRequest.username());
        account.setPassword(passwordEncoder.encode(signupRequest.password()));
        account.setEmployee(currentEmployee);
        userAccountRepo.save(account);

    };
}
