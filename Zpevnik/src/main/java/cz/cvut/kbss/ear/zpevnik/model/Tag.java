/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Adam Škarda
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name LIKE :name"),
})
public class Tag extends AbstractEntity{
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @ManyToMany
    private List<Song> song = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Song> getSong() {
        return song;
    }

    public void setSong(List<Song> song) {
        this.song = song;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
