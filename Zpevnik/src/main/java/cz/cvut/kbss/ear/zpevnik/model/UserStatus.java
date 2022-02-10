/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.model;

/**
 *
 * @author Adam Škarda
 */
public enum UserStatus {
    ACTIVE("STATUS_ACTIVE"),
    DISABLED("STATUS_DISABLED");
    
    private final String name;
    
    private UserStatus(String name) {    
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
