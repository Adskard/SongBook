/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Adam Škarda
 */
public class InvalidUserCredentialsException extends AuthenticationException{
    public InvalidUserCredentialsException() {
        super(null);
    }

    public InvalidUserCredentialsException(String msg) {
        super(msg);
    }

    public InvalidUserCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
}
