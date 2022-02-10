/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service;

import cz.cvut.kbss.ear.zpevnik.dao.TagDao;
import cz.cvut.kbss.ear.zpevnik.model.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adam Škarda
 */
@Service
public class TagService extends AbstractService<Tag>{

    
    private final TagDao dao;
    
    @Autowired
    public TagService(TagDao dao) {
        super(dao);
        this.dao = dao;
    }
    
    @Transactional
    public List<Tag> findByName(String name){
        return dao.findByName(name);
    }
    
}
