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
public enum SecurityConstants {
    USERNAME_PARAM("username"),
    PASSWORD_PARAM("password"),
    SECURITY_CHECK_URI("/user/login"),
    LOGOUT_URI("/user/logout"),
    REGISTER_URI("/user/register"),
    HOME_URI("/");
    
    private final String stringVal;
    SecurityConstants(String s){
        this.stringVal = s;
    }

    public String getStringVal() {
        return stringVal;
    }
    
}
