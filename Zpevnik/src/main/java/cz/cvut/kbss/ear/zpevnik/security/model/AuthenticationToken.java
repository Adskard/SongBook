/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 *
 * @author Adam Škarda
 */
public class AuthenticationToken extends AbstractAuthenticationToken{

    private final UserDetails userDetails;

    public AuthenticationToken(UserDetails userDetails) {
        super(userDetails.getAuthorities());
        this.userDetails = userDetails;
        super.setAuthenticated(true);
        super.setDetails(userDetails);
    }

    @Override
    public String getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public UserDetails getPrincipal() {
        return userDetails;
    }
}
