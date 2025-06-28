package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import com.employeeManagmentSystem.employeeManagmentSystem.Exceptions.CustomizedExceptionHandler;
import com.employeeManagmentSystem.employeeManagmentSystem.Repositories.UserAccountRepo;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private  UserAccountRepo userAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccount = userAccountRepo.findByUsername(username);

        if(userAccount.isEmpty()){
            throw CustomizedExceptionHandler.BadCredentials();
        }

        UserAccount user = userAccount.get();

        System.out.println("Loaded user: " + user.getUsername() + ", password: " + user.getPassword());

        return User.builder().username(user.getUsername()).
                password(user.getPassword()).
                roles(user.getRole()).build();
    }
}
