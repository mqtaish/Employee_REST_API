package com.employeeManagmentSystem.employeeManagmentSystem.Controller;

import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.LoginRequest;
import com.employeeManagmentSystem.employeeManagmentSystem.DTOs.SignupRequest;
import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.GlobalResponse;
import com.employeeManagmentSystem.employeeManagmentSystem.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<String>> login
        (@RequestBody LoginRequest loginRequest){
       String token =  authService.login(loginRequest);
        return new ResponseEntity<GlobalResponse<String>>(
                new GlobalResponse<>(token), HttpStatus.CREATED);
    }

    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<String>> signup
            (@RequestBody SignupRequest signupRequest){
        System.out.println("we are register a new account");
        authService.signup(signupRequest);
        return new ResponseEntity<GlobalResponse<String>>(
                new GlobalResponse<>(""), HttpStatus.CREATED);
    }


}
