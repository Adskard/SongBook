/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import cz.cvut.kbss.ear.zpevnik.model.Admin;
import cz.cvut.kbss.ear.zpevnik.model.Artist;
import cz.cvut.kbss.ear.zpevnik.model.RegularUser;
import cz.cvut.kbss.ear.zpevnik.model.Song;
import cz.cvut.kbss.ear.zpevnik.model.VerifiedUser;

/**
 *
 * @author Adam Škarda
 */
public class Generator {
    
    private static Integer id = 1;
    
    public static RegularUser generateRegularUser(){
        RegularUser user = new RegularUser();
        user.setUsername("username" + id.toString());
        user.setPassword("pswd" + id.toString());
        user.setId(id);
        id++;
        return user;
    }
    
    public static Admin generateAdmin(){
        Admin a = new Admin();
        a.setId(id ++);
        a.setUsername("adminName " + a.getId());
        a.setPassword("adminPswd" + a.getId());
        return a;
    }
    
    public static VerifiedUser generateVerifiedUser(){
        VerifiedUser a = new VerifiedUser();
        a.setId(id ++);
        a.setUsername("verifiedUserName" + a.getId());
        a.setPassword("verifiedUserPswd" + a.getId());
        return a;
    }
    
    public static Artist generateArtist(){
        Artist art = new Artist();
        art.setId(id++);
        art.setBio("Long may he live" + art.getId());
        art.setName("artist Number: " + art.getId());
        return art;
    }
    
    public static Song generateSong(){
        Song s = new Song();
        s.setName("SongName"+ id.toString());
        s.setText("SongText " + id.toString());
        s.setId(id);
        id++;
        return s;
    }
}
