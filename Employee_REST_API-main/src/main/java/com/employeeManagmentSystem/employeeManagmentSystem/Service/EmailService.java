package com.employeeManagmentSystem.employeeManagmentSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${backend.origin}")
    private String ORIGIN;

    @Value("${GMAIL_APP_EMAIL}")
    private String from;


    public void sendAccountCreationEmail(String to, String token){
        String link = ORIGIN + "/auth/signup?token= " + token;

        SimpleMailMessage simpleMailMessage  = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Create Your Account!");
        simpleMailMessage.setText("Kindly request to open an account with us to get further updates\n" + link);
        javaMailSender.send(simpleMailMessage);
    }

}

