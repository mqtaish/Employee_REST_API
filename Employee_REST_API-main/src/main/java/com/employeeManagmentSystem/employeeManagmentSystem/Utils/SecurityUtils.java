package com.employeeManagmentSystem.employeeManagmentSystem.Utils;

import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityUtils {

    @Autowired
    private UserAccountRepo userAccountRepo;
    public boolean  isOwner(UUID incomingEmployeeId){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println("HERE HERE" + userDetails.getUsername());
        System.out.println("HERE HERE" + incomingEmployeeId);

        return userAccountRepo.isOwner(userDetails.getUsername(), incomingEmployeeId);

    }
}
