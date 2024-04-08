package com.example.jdbctemplate_miniproject.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String,String>> errorHandling(MethodArgumentNotValidException ex, HttpServletRequest request)
        {
            Map<String,String> errorInfo = new LinkedHashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error)->{
                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                log.error(String.format("Validation error occurred at URI: %s : 400 : %s, %s",request.getRequestURI(),request.getMethod(),message));
                errorInfo.put(fieldName,message);
            });
                    return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
        }
}
