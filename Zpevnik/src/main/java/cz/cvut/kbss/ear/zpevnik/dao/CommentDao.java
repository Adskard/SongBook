/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import cz.cvut.kbss.ear.zpevnik.model.Comment;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adam Škarda
 */
@Repository
public class CommentDao extends AbstractDao<Comment>{

    public CommentDao() {
        super(Comment.class);
    }
    
}
