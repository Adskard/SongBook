/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service;

import cz.cvut.kbss.ear.zpevnik.dao.SongBookDao;
import cz.cvut.kbss.ear.zpevnik.model.SongBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Škarda
 */
@Service
public class SongBookService extends AbstractService<SongBook>{

    
    private final SongBookDao dao;

    @Autowired
    public SongBookService(SongBookDao dao) {
        super(dao);
        this.dao = dao;
    }
}
