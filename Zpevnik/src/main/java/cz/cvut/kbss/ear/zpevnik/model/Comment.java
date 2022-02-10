/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Adam Škarda
 */
@Entity
public class Comment extends AbstractEntity{
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private User author;
    
    @Lob
    @Column(nullable = false)
    private String content;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Song song;

    public User getAuthor() {
        return author;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
