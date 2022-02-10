/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Adam Škarda
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Song.popularSongs", query = "SELECT s FROM Song s ORDER BY s.visits DESC"),
        @NamedQuery(name = "Song.findByName", query = "SELECT s FROM Song s WHERE s.name LIKE :name")
})
public class Song extends AbstractEntity{
    
    @ManyToOne
    private Artist artist;
    
    @ManyToOne
    private VerifiedUser author;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();
    
    @Column(nullable = false)
    private String name;
    
    @ManyToMany(mappedBy = "song", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Tag> tags = new ArrayList<>();
    
    @Lob
    @Column(nullable = false)
    private String text;

    private Long visits;

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public VerifiedUser getAuthor() {
        return author;
    }

    public void setAuthor(VerifiedUser author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
