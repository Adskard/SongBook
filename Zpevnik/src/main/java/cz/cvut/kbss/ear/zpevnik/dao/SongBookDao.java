/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import cz.cvut.kbss.ear.zpevnik.model.SongBook;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adam Škarda
 */
@Repository
public class SongBookDao extends AbstractDao<SongBook>{

    public SongBookDao() {
        super(SongBook.class);
    }
}
