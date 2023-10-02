package com.vasudev.blog.exceptions;

import com.vasudev.blog.entities.User;
import com.vasudev.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        String message=e.getMessage();
        ApiResponse apiResponse= new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
                Map<String,String > resp= new HashMap<>();
                methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
                 String fieldName= ((FieldError)error).getField();
                 String message= error.getDefaultMessage();
                 resp.put(fieldName,message);
                });
                return new ResponseEntity<Map<String,String >>(resp, HttpStatus.BAD_REQUEST);
    }

@ExceptionHandler(TransactionSystemException.class)
public ResponseEntity<Map<String,String >> handleTransactionSystemException(TransactionSystemException ex) {
    String fieldName= ex.getLocalizedMessage();
    String message=ex.getMessage();
    Map<String,String > resp= new HashMap<>();
    resp.put(fieldName,message);
    // Handle the exception and return an appropriate response
    return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
}






}
