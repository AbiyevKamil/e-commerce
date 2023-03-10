package com.kamilabiyev.ecommerce.domain.exception.handler;


import com.kamilabiyev.ecommerce.domain.exception.*;
import com.kamilabiyev.ecommerce.domain.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(DataExistsException.class)
    public ResponseEntity<ErrorResponse> handleDataExistsException(DataExistsException e,
                                                               HttpServletRequest request) {
        //        String method = request.getMethod();
        //        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //        String url = request.getRequestURL().toString();
        //        var logString = String.format("Method: %s; \n\n User: %s; \n\n Requested url: %s", method, username, url);
        //        log.info(logString);
        var response = new ErrorResponse(List.of(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException e,
                                                               HttpServletRequest request) {
        var response = new ErrorResponse(List.of(e.getMessage()), HttpStatus.NOT_FOUND, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e,
                                                               HttpServletRequest request) {
        var response = new ErrorResponse(List.of(e.getMessage()), HttpStatus.UNAUTHORIZED, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException e,
                                                               HttpServletRequest request) {
        var response = new ErrorResponse(List.of(e.getMessage()), HttpStatus.UNAUTHORIZED, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCommonException(Exception e,
                                                               HttpServletRequest request) {
        var response = new ErrorResponse(List.of(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(DisabledException.class)
    protected ResponseEntity<Object> handleDisabledException(DisabledException e, HttpServletRequest request) {
        var response = new ErrorResponse(List.of(e.getMessage()), HttpStatus.UNAUTHORIZED, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(InvalidFileException.class)
    protected ResponseEntity<Object> handleInvalidFileException(InvalidFileException e, HttpServletRequest request) {
        var response = new ErrorResponse(List.of(e.getMessage()), HttpStatus.BAD_REQUEST, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request) {
        var response = new ErrorResponse(List.of(e.getMessage()), HttpStatus.UNAUTHORIZED, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

}

