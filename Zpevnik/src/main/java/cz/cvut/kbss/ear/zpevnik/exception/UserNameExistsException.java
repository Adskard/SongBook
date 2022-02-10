/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.exception;

/**
 *
 * @author Adam Škarda
 */
public class UserNameExistsException extends RuntimeException{
    public UserNameExistsException() {
    }

    public UserNameExistsException(String message) {
        super(message);
    }

    public UserNameExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameExistsException(Throwable cause) {
        super(cause);
    }
}
