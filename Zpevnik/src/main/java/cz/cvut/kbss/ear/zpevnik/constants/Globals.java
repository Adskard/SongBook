/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.constants;

/**
 *
 * @author Adam Škarda
 */
public enum Globals {
    ITEMS_PER_PAGE("5");
    
    private final String stringVal;
    Globals(String s){
        this.stringVal = s;
    }

    public String getStringVal() {
        return stringVal;
    }
}
