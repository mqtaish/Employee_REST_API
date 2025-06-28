package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.Config.JwtHelper;
import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.LoginRequest;
import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.SignupRequest;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.CustomizedExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.EmployeeRepo;
import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.UserAccountRepo;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.Employee;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService  {

    @Autowired
    private UserAccountRepo userAccountRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    public void signup(SignupRequest signupRequest, String token){
        Employee currentEmployee = employeeRepo.findOneByAccountCreationToken(token)
                .orElseThrow(()-> CustomizedExceptionHandler.BadRequest("Invalid Token"));

        if (currentEmployee.isVerified()){
            throw  CustomizedExceptionHandler.BadRequest("Account Already exists");
        }

        System.out.println("we are register a new account in database");
        UserAccount account = new UserAccount();
        account.setUserName(signupRequest.username());
        account.setPassword(passwordEncoder.encode(signupRequest.password()));
        account.setEmployee(currentEmployee);
        userAccountRepo.save(account);

        currentEmployee.setVerified(true);
        currentEmployee.setAccountCreationToken(null);
        employeeRepo.save(currentEmployee);

    };

    public String login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        UserAccount userAccount = userAccountRepo.findByUsername(loginRequest.username())
                        .orElseThrow();

        if (userAccount != null){
            System.out.println("My User" + userAccount.getRole());
        }
                return jwtHelper.generateToken(userAccount);

    };


}
