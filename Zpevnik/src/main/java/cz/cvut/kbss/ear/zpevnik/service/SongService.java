/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service;

import cz.cvut.kbss.ear.zpevnik.dao.SongDao;
import cz.cvut.kbss.ear.zpevnik.model.Song;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Škarda
 */
@Service
public class SongService extends AbstractService<Song>{

    
    private final SongDao dao;

    @Autowired
    public SongService(SongDao dao) {
        super(dao);
        this.dao = dao;
    }
    
    @Transactional
    public List<Song> findByName(String name){
        return dao.findByName(name);
    }
    
    @Transactional
    public List<Song> findTopPopularSongs(Integer num){
        return dao.findPopularSongs(num);
    }
    
    @Transactional
    @Override
    public void persist(Song entity) {
        
        entity.setVisits(0L);
        dao.persist(entity);
    }
}
