 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.security;

import cz.cvut.kbss.ear.zpevnik.model.User;
import cz.cvut.kbss.ear.zpevnik.security.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Adam Škarda
 */
public class SecurityUtils {
    private static final Logger log = LoggerFactory.getLogger(SecurityUtils.class);
    public static User getCurrentUser() {
        final SecurityContext context = SecurityContextHolder.getContext();
        assert context != null;
        log.debug(context.getAuthentication().getName());
        final UserDetails userDetails = (UserDetails) context.getAuthentication().getDetails();
        return userDetails.getUser();
    }
    
    public static UserDetails getCurrentUserDetails() {
        final SecurityContext context = SecurityContextHolder.getContext();
        assert context != null;
        log.debug(context.getAuthentication().getName());
        final UserDetails userDetails = (UserDetails) context.getAuthentication().getDetails();
        return userDetails;
    }
    
    public static boolean isAuthenticatedAnonymously() {
        return getCurrentUserDetails() == null;
    }
}
