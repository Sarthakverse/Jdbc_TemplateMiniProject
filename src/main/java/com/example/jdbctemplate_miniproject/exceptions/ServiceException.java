package com.example.jdbctemplate_miniproject.exceptions;

// extending runtimeException makes the exception unchecked and therefore no need to declare it with method name
public class ServiceException extends RuntimeException{

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
