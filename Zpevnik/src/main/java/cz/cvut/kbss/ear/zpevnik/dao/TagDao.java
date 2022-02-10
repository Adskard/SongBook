/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import cz.cvut.kbss.ear.zpevnik.model.Tag;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adam Škarda
 */
@Repository
public class TagDao extends AbstractDao<Tag>{

    public TagDao() {
        super(Tag.class);
    }
    
    public List<Tag> findByName(String name){
        try{
            List<Tag> t = em.createNamedQuery("Tag.findByName", Tag.class).setParameter("name", "%"+name+"%").getResultList();
            return t;
        }
        catch(NoResultException ex){
            return null;
        }
    }
}
