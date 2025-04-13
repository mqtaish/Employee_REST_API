package com.employeeManagmentSystem.employeeManagmentSystem.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<GlobalResponse<?>> handleNoResponseException(NoHandlerFoundException ex){
        var errorItemList = List.of(
                new GlobalResponse.ErrorItem("Resource is not found")
        );
        return new ResponseEntity<>(new GlobalResponse<>(errorItemList), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomizedExceptionHandler.class)
    public ResponseEntity<GlobalResponse<?>> handleNoResourceException(CustomizedExceptionHandler ex){
        var errorItemList = List.of(
                new GlobalResponse.ErrorItem(ex.getMessage())
        );
        return new ResponseEntity<>(new GlobalResponse<>(errorItemList), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse<?>> NoValidiationException(MethodArgumentNotValidException ex){
        List<GlobalResponse.ErrorItem> errorItemList = ex.getBindingResult().getFieldErrors().stream().
                map(error -> new GlobalResponse.ErrorItem(error.getField() + " "+ error.getDefaultMessage())).toList();
        System.out.println("Validation Hanlder Exception\n");
        return new ResponseEntity<>(new GlobalResponse<>(errorItemList), HttpStatus.BAD_REQUEST);
    }

}


