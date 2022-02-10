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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Adam Škarda
 */
@Entity
@DiscriminatorValue(UserRoles.Values.VERIFIED)
@NamedQueries({
    @NamedQuery(name="VerifiedUser.findByEmail", query="SELECT e FROM VerifiedUser e WHERE e.email = :email")
})
public class VerifiedUser extends RegularUser{

    @OneToMany(mappedBy = "author", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Song> songs = new ArrayList<>();
    
    @Column( unique = true)
    private String email;
    
    @Column(nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
    
}
