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
public class InformationMismatchException extends RuntimeException{

    public InformationMismatchException() {
    }

    public InformationMismatchException(String message) {
        super(message);
    }

    public InformationMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public InformationMismatchException(Throwable cause) {
        super(cause);
    }
}
