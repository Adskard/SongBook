/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service;

import cz.cvut.kbss.ear.zpevnik.dao.ArtistDao;
import cz.cvut.kbss.ear.zpevnik.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Škarda
 */
@Service
public class ArtistService extends AbstractService<Artist>{
    
    private final ArtistDao dao;

    @Autowired
    public ArtistService(ArtistDao dao) {
        super(dao);
        this.dao = dao;
    }
}
