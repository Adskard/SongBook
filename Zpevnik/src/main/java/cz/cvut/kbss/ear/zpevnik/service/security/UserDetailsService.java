/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service.security;

import cz.cvut.kbss.ear.zpevnik.dao.UserDao;
import cz.cvut.kbss.ear.zpevnik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Škarda
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    private final UserDao userDao;
    @Autowired
    public UserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Account with username: " + username + " not found.");
        }
        return new cz.cvut.kbss.ear.zpevnik.security.model.UserDetails(user);
    }
    
}
