/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service;

import cz.cvut.kbss.ear.zpevnik.dao.CommentDao;
import cz.cvut.kbss.ear.zpevnik.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Škarda
 */
@Service
public class CommentService extends AbstractService<Comment>{
    
    private final CommentDao dao;
    
    @Autowired
    public CommentService(CommentDao dao) {
        super(dao);
        this.dao = dao;
    }
}
