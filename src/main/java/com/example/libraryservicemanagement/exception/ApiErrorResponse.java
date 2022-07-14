package com.example.libraryservicemanagement.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ApiErrorResponse {

    private Date timestamp;
    private int statusCode;
    private String message;
    private String fieldName;
}
