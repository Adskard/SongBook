/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import cz.cvut.kbss.ear.zpevnik.model.Song;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adam Škarda
 */
@Repository
public class SongDao extends AbstractDao<Song>{
    
    public SongDao(){
        super(Song.class);
    }
    
    public List<Song> findPopularSongs(Integer number){
        try{
            return em.createNamedQuery("Song.popularSongs", Song.class)
                .setMaxResults(number)
                .getResultList();
        }
        catch(NoResultException ex){
            return new ArrayList<>();
        }
    }
    
    public List<Song> findByName(String name){
        try{
            return em.createNamedQuery("Song.findByName", Song.class)
                    .setParameter("name", "%" + name + "%") 
                    .getResultList();
        }
        catch(NoResultException ex){
            return new ArrayList<>();
        }
    }
    
}
