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
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author Adam Škarda
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType",
        discriminatorType = DiscriminatorType.STRING)
@Table(name = "APPUSER")
@NamedQueries({
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.username = :name"),
})
public abstract class User extends AbstractEntity{

    @OneToMany(mappedBy = "author", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("date DESC")
    private List<Comment> comments = new ArrayList<>();
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SongBook songBook;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    //60 min for encoder
    @Column(nullable = false, length = 100)
    private String password;
    
    public String getType() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public SongBook getSongBook() {
        return songBook;
    }
    

    public void setSongBook(SongBook songBook) {
        this.songBook = songBook;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
