/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service;

import cz.cvut.kbss.ear.zpevnik.dao.UserDao;
import cz.cvut.kbss.ear.zpevnik.exception.EmailExistsException;
import cz.cvut.kbss.ear.zpevnik.exception.UserNameExistsException;
import cz.cvut.kbss.ear.zpevnik.model.RegularUser;
import cz.cvut.kbss.ear.zpevnik.model.SongBook;
import cz.cvut.kbss.ear.zpevnik.model.User;
import cz.cvut.kbss.ear.zpevnik.model.UserRoles;
import cz.cvut.kbss.ear.zpevnik.model.UserStatus;
import cz.cvut.kbss.ear.zpevnik.model.VerifiedUser;
import cz.cvut.kbss.ear.zpevnik.security.SecurityUtils;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adam Škarda
 */
@Service
public class UserService extends AbstractService<User>{
    
    protected final UserDao userDao;
    private final PasswordEncoder encoder;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    public UserService(UserDao userDao, PasswordEncoder encoder) {
        super(userDao);
        this.userDao = userDao;
        this.encoder = encoder;
    }
    
    //CREATE ===================================================================
    @Transactional
    public void registerUser(String username, String password) throws UserNameExistsException{
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        if(usernameExists(username)){
            throw new UserNameExistsException("Account with username: " 
            + username + " already exists!");
        }
        RegularUser user = new RegularUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        assignSongBook(user); //Song book cascades
        userDao.persist(user);
    }
    
    @Transactional
    public void registerVerifiedUser(String username, String password, String email)
            throws EmailExistsException{
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        Objects.requireNonNull(email);
        if(usernameExists(username)){
            throw new UserNameExistsException("Account with username: " 
            + username + " already exists!");
        }
        if(userDao.findByEmail(email) != null){
            throw new EmailExistsException("This email is taken");
        }
        VerifiedUser user = new VerifiedUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        assignSongBook(user); //Song book cascades
        userDao.persist(user);
    }
    
    @Transactional
    @Override
    public void persist(User user) throws UserNameExistsException{
        Objects.requireNonNull(user);
        if(usernameExists(user.getUsername())){
            throw new UserNameExistsException("Account with username: " 
            + user.getUsername() + " already exists!");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.persist(user);
    }
    //READ =====================================================================
    @Transactional
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
    
    @Transactional
    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }
    
    @Transactional
    public boolean usernameExists(String username){
        return null != userDao.findByUsername(username);
    }
    
    //ADMIN
    @Transactional
    public void changeUserState(Integer uId, UserStatus status){
        User currentUser = SecurityUtils.getCurrentUser();
        RegularUser u = (RegularUser) userDao.find(uId);
        if(currentUser.getType().equals(UserRoles.ADMIN.toString())){
            log.debug("Changed"+ u.getType() +" user status: " +u.getStatus()+ "-"+status);
            u.setStatus(status);
            userDao.update((User)u);
        }
    }
    
    private void assignSongBook(User u){
        SongBook sb = new SongBook();
        sb.setUser(u);
        u.setSongBook(sb);
    }
}
