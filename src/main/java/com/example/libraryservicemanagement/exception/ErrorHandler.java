package com.example.libraryservicemanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ErrorHandler {

    private Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.info("");
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiErrorResponse.setFieldName(ex.getFieldError().getField());
        apiErrorResponse.setMessage(ex.getFieldError().getDefaultMessage());
            apiErrorResponse.setTimestamp(new Date());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> onMissingServletRequestParameterException(MissingServletRequestParameterException ex){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiErrorResponse.setFieldName(ex.getParameterName());
            apiErrorResponse.setMessage(ex.getMessage());
            apiErrorResponse.setTimestamp(new Date());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookBorrowIsNotAllowed.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(final BookBorrowIsNotAllowed ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        if (ex.getMessage() != null) {
            apiErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiErrorResponse.setFieldName(null);
            apiErrorResponse.setMessage(ex.getMessage());
            apiErrorResponse.setTimestamp(new Date());
        }
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
