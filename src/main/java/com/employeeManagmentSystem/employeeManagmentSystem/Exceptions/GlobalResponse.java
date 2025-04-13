package com.employeeManagmentSystem.employeeManagmentSystem.Exceptions;

import java.util.List;

public class GlobalResponse<T> {

    private final  static String  SUCCESS  = "success";
    private final  static String  ERROR  = "error";
    private  final String status;
    private  final T Data ;
    private final  List<ErrorItem> errors;
    public GlobalResponse(T data) {
        this.status = SUCCESS;
        this.Data = data;
        this.errors  = null;
    }
    public GlobalResponse(List<ErrorItem>errors) {
        this.status = ERROR;
        this.Data = null;
        this.errors = errors;
    }
    public T getData() {
        return Data;
    }
    public record ErrorItem(String message){}

    public String getStatus() {
        return status;
    }

    public List<ErrorItem> getErrors() {
        return errors;
    }
}
