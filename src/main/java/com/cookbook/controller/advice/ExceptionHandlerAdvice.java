package com.cookbook.controller.advice;

import com.cookbook.domain.exception.ExceptionMessage;
import com.cookbook.domain.exception.GenericException;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleResourceNotFoundException(NoResultException exp,
                                                                            HttpServletRequest req){
        var response = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), req.getRequestURI(), exp.getMessage());
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleGenericException(GenericException exp, HttpServletRequest req){
        var response = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), req.getRequestURI(), exp.getMessage());
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ExceptionMessage> handlePropertyReferenceException(PropertyReferenceException exp, HttpServletRequest req){
        var response = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), req.getRequestURI(), exp.getMessage());
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

}
