/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import cz.cvut.kbss.ear.zpevnik.ZpevnikApp;
import cz.cvut.kbss.ear.zpevnik.model.Song;
import cz.cvut.kbss.ear.zpevnik.service.SystemInitializer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import util.Generator;

/**
 *
 * @author Adam Škarda
 */
@DataJpaTest
@ComponentScan(basePackageClasses = ZpevnikApp.class,excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SystemInitializer.class)})
public class SongDaoTest {
    
    @Autowired
    private TestEntityManager em;
    
    @Autowired
    private SongDao dao;

    @Test
    public void findPopularSongs_TwoSongsInDB_findsTwoSongsSwapsOrder() {
        //arrange
        Song song = Generator.generateSong();
        song.setVisits(Long.MIN_VALUE);
        
        Song song2 = Generator.generateSong();
        song2.setVisits(Long.MAX_VALUE);
        
        List<Song> expResult = new ArrayList<>();
        expResult.add(song2);
        expResult.add(song);
        
        em.persist(song);
        em.persist(song2);
        //act 
        List<Song> result = dao.findPopularSongs(2);
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        //assert
        assertEquals(expResult, result);
    }
    
    @Test
    public void findPopularSongs_NoSongsInDB_returnsEmptyList(){
        //arrange
        //act
        List<Song> result = dao.findPopularSongs(3);
        //assert
        assertArrayEquals(new Song[]{}, result.toArray());
    }
    
    @Test
    public void findPopularSongs_OneSongInDbWithNullVisitsAskFor4Songs_returnsOneSong(){
        //arrange
        Song song = Generator.generateSong();
        
        List<Song> expResult = new ArrayList<>();
        expResult.add(song);
        
        em.persist(song);
        
        //act
        List<Song> result = dao.findPopularSongs(4);
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        //assert
        assertArrayEquals(expResult.toArray(), result.toArray());
    }
    
    @Test
    public void findByName_distinctSongsInDB_returnsOneSong(){
        //arrange
        List<Song> expResult = new ArrayList<>();
        Song song = Generator.generateSong();
        song.setName("Pepa");
        em.persist(song);
        
        song = Generator.generateSong();
        song.setName("Hroch");
        expResult.add(song);
        em.persist(song);
        //act
        List<Song> result = dao.findByName("Hroch");
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        //assert
        assertEquals(expResult, result);
    }
    
    @Test
    public void findByName_similarSongsInDB_returnsTwoSongs(){
        //arrange
        List<Song> expResult = new ArrayList<>();
        Song song = Generator.generateSong();
        song.setName("Hroch (ver2)");
        em.persist(song);
        expResult.add(song);
        
        song = Generator.generateSong();
        song.setName("Hroch");
        expResult.add(song);
        em.persist(song);
        //act
        List<Song> result = dao.findByName("Hroch");
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        //assert
        assertEquals(expResult, result);
    }
    
    @Test
    public void findByName_emptyDB_returnsNull(){
        //arrange        
        //act
        List<Song> result = dao.findByName("Hroch");
        
        //assert
        assertTrue(result.isEmpty());
    }
    
}
