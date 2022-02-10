/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import cz.cvut.kbss.ear.zpevnik.model.Admin;
import cz.cvut.kbss.ear.zpevnik.model.RegularUser;
import cz.cvut.kbss.ear.zpevnik.model.User;
import cz.cvut.kbss.ear.zpevnik.model.VerifiedUser;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adam Škarda
 */
@Repository
public class UserDao extends AbstractDao<User>{

    public UserDao() {
        super(User.class);
    }
    
    public User findByUsername(String username){
        try{
            User u = em.createNamedQuery("User.findByName", User.class).setParameter("name", username).getSingleResult();
            return u;
        }
        catch(NoResultException ex){
            return null;
        }
    }
    
    public List<RegularUser> findAllRegularUsers(){
        try{
            List<RegularUser> users = em.createQuery("SELECT u FROM " + User.class.getSimpleName() +" u", RegularUser.class).getResultList();
            return users;
        }
        catch(NoResultException ex){
            return new ArrayList<>();
        }
    }
    
    public List<VerifiedUser> findAllVerifiedUsers(){
        try{
            List<VerifiedUser> users = em.createQuery("SELECT u FROM " + User.class.getSimpleName() +" u", VerifiedUser.class).getResultList();
            return users;
        }
        catch(NoResultException ex){
            return new ArrayList<>();
        }
    }
    
    public List<Admin> findAllAdminUsers(){
        try{
            List<Admin> users = em.createQuery("SELECT u FROM " + User.class.getSimpleName() +" u", Admin.class).getResultList();
            return users;
        }
        catch(NoResultException ex){
            return new ArrayList<>();
        }
    }
    
    public User findByEmail(String mail){
        try{
            return em.createNamedQuery("VerifiedUser.findByEmail",VerifiedUser.class)
                    .setParameter("email", mail)
                    .getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }
    }
}
