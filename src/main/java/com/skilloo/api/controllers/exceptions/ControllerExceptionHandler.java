package com.skilloo.api.controllers.exceptions;

import com.skilloo.api.controllers.exceptions.validation.ValidationError;
import com.skilloo.api.services.exceptions.*;
import com.skilloo.api.services.exceptions.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.Arrays;

@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> runtime(RuntimeException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Internal Error: " + e.getClass());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<StandardError> dataNotFound(DataNotFoundException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Data not found!");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
    public ResponseEntity<StandardError> unauthorized(AuthenticationException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("UNAUTHORIZED");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<StandardError> forbidden(ForbiddenException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.FORBIDDEN;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("FORBIDDEN!");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(NenhumaAulaAtribuidaException.class)
    public ResponseEntity<StandardError> nenhumaAulaAtribuida(NenhumaAulaAtribuidaException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Nenhuma Aula Encontrada");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Database Integrity Violation Exception!");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> enumException(HttpMessageNotReadableException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Field Exception!");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> enumPathVariableException(MethodArgumentTypeMismatchException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Enum Path Variable Exception!");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }



    @ExceptionHandler(TokenException.class)
    public ResponseEntity<StandardError> validation(TokenException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());


        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        for(FieldError f : e.getBindingResult().getFieldErrors()){
            error.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }



}
