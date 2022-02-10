/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.rest;

import cz.cvut.kbss.ear.zpevnik.exception.AuthorizationException;
import cz.cvut.kbss.ear.zpevnik.exception.EmailExistsException;
import cz.cvut.kbss.ear.zpevnik.exception.InformationMismatchException;
import cz.cvut.kbss.ear.zpevnik.exception.InvalidRequestException;
import cz.cvut.kbss.ear.zpevnik.exception.InvalidUserCredentialsException;
import cz.cvut.kbss.ear.zpevnik.exception.ItemNotFoundException;
import cz.cvut.kbss.ear.zpevnik.exception.UserNameExistsException;
import javax.persistence.PersistenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Adam Škarda
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest req){
        String responseBody = "RuntimeException encountered! " + ex.getMessage();
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(),
                HttpStatus.CONFLICT, req);
    }
    
    @ExceptionHandler(value = {PersistenceException.class})
    protected ResponseEntity<Object> handleConflict(PersistenceException ex, WebRequest req){
        String responseBody = "Database access exception encountered!";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, req);
    }
    
    @ExceptionHandler(value = {ItemNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(ItemNotFoundException ex, WebRequest req){
        String responseBody = "Item not found!";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(),
                HttpStatus.NOT_FOUND, req);
    }
    
    @ExceptionHandler(value = {InvalidRequestException.class})
    protected ResponseEntity<Object> handleConflict(InvalidRequestException ex, WebRequest req){
        String responseBody = "Request Failed: ";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, req);
    }
    
    @ExceptionHandler(value = {InformationMismatchException.class})
    protected ResponseEntity<Object> handleConflict(InformationMismatchException ex, WebRequest req){
        String responseBody = "Information does not match";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(),
                HttpStatus.NOT_ACCEPTABLE, req);
    }
    
    @ExceptionHandler(value = {InvalidUserCredentialsException.class})
    protected ResponseEntity<Object> handleConflict(InvalidUserCredentialsException ex, WebRequest req){
        String responseBody = "Wrong credetials!";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(),
                HttpStatus.NOT_ACCEPTABLE, req);
    }
    
    @ExceptionHandler(value = {UserNameExistsException.class})
    protected ResponseEntity<Object> handleConflict(UserNameExistsException ex, WebRequest req){
        String responseBody = "This username already exists!";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(),
                HttpStatus.NOT_ACCEPTABLE, req);
    }
    
    @ExceptionHandler(value = {EmailExistsException.class})
    protected ResponseEntity<Object> handleConflict(EmailExistsException ex, WebRequest req){
        String responseBody = "This email is already used!";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(),
                HttpStatus.NOT_ACCEPTABLE, req);
    }
    
    @ExceptionHandler(value = {AuthorizationException.class})
    protected ResponseEntity<Object> handleConflict(AuthorizationException ex, WebRequest req){
        String responseBody = "You dont have the authority to do THIS!";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(),
                HttpStatus.UNAUTHORIZED, req);
    }
}
