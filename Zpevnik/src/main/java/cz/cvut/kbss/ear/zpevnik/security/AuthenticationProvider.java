/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.security;

import cz.cvut.kbss.ear.zpevnik.exception.InvalidUserCredentialsException;
import cz.cvut.kbss.ear.zpevnik.security.model.AuthenticationToken;
import cz.cvut.kbss.ear.zpevnik.security.model.UserDetails;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Škarda
 */
@Service
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider  {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationProvider.class);

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;  
    
    @Autowired
    public AuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getPrincipal().toString();
        String pswd = a.getCredentials().toString();
        UserDetails user = (UserDetails) userDetailsService.loadUserByUsername(username);
        log.debug("Auth: " + username + " " +pswd +"|||| User: " + user.getPassword() + " " +user.getUsername()
        + " AUTHORITIES : "+ user.getAuthorities().toString());
        if(Objects.isNull(user)){
            throw new InvalidUserCredentialsException("Account with username " + username + " not found!");
        }
        
        if(passwordEncoder.matches(pswd, user.getPassword())){
            final AuthenticationToken token = new AuthenticationToken(user);
            final SecurityContext context = new SecurityContextImpl();
            context.setAuthentication(token);
            SecurityContextHolder.setContext(context);
            return token;
        }
        else{
            throw new InvalidUserCredentialsException("Passwords do not match!");
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(type) ||
                AuthenticationToken.class.isAssignableFrom(type);
    }
    
}
