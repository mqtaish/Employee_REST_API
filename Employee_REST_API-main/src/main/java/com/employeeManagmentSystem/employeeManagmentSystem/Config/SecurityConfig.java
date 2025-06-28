package com.employeeManagmentSystem.employeeManagmentSystem.Config;

import com.employeeManagmentSystem.employeeManagmentSystem.Service.AuthService;
import com.employeeManagmentSystem.employeeManagmentSystem.Service.UserDetailsServiceImpl;
import com.employeeManagmentSystem.employeeManagmentSystem.entities.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpRequest;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private final UserDetailsServiceImpl authService;

    public SecurityConfig(UserDetailsServiceImpl authService) {
        this.authService = authService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for API
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/auth/signup","/auth/login").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/employees").hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/api/employees").hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/employees{employeesId}").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.POST, "/api/employees/{employeeId}/leave-request").hasAnyRole("USER","ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/employees/{employeeId}/leave-request").hasAnyRole("USER", "ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/employees{employeesId}").hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/api/employees{employeesId}").hasAnyRole("ADMIN")
                            .anyRequest()
                            .authenticated();

                           // .anyRequest().authenticated();

                })
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager(http));


        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(authService).passwordEncoder(passwordEncoder());
        return authBuilder.build();

    }
}
