package com.employeeManagmentSystem.employeeManagmentSystem.Exceptions;

import lombok.AllArgsConstructor;


public class CustomizedExceptionHandler  extends RuntimeException {
    private  int code;
    private  String message;

    public CustomizedExceptionHandler(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CustomizedExceptionHandler ResourseNotFound(String message){
        return new CustomizedExceptionHandler(404, message);
    }

    public static CustomizedExceptionHandler BadCredentials(){
        return new CustomizedExceptionHandler(403, "Bad Credentials");
    }

    public static CustomizedExceptionHandler BadRequest(String message){
        return new CustomizedExceptionHandler(400, message);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
