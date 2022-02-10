/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import cz.cvut.kbss.ear.zpevnik.ZpevnikApp;
import cz.cvut.kbss.ear.zpevnik.model.Artist;
import cz.cvut.kbss.ear.zpevnik.service.SystemInitializer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
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
public class AbstractDaoTest {
    
    
    @Autowired
    private TestEntityManager em;

    @Autowired
    private ArtistDao dao;
    
    @Test
    public void find_findExistingObject(){
        //arrange
        Artist expResult = Generator.generateArtist();
        em.persist(expResult);
        
        //act
        Artist result = dao.find(expResult.getId());
        
        //assert
        assertEquals(expResult.getId(), result.getId());
    }
    
    @Test
    public void find_findObjectNotInDB(){
        //arrange
        Artist someArtist = Generator.generateArtist();
        
        //act
        Artist result = dao.find(someArtist.getId());
        
        //assert
        assertNull(result);
        
    }
    
    @Test
    public void findAll_EmptyDB(){
        //arrange
        //act
        List<Artist> result = dao.findAll();
        //assert
        assertTrue(result.isEmpty());
    }
    
    @Test
    public void findAll_findsTwoArtists(){
        //arrange
        Artist a1 = Generator.generateArtist();
        Artist a2 = Generator.generateArtist();
        em.persist(a1);
        em.persist(a2);
        
        List<Artist> expResult = new ArrayList<>();
        expResult.add(a2);
        expResult.add(a1);
        
        
        //act
        List<Artist> result = dao.findAll();
        
        //assert
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        assertEquals(expResult, result);
    }
    
    @Test
    public void countAll_counts2(){
        Artist a1 = Generator.generateArtist();
        Artist a2 = Generator.generateArtist();
        em.persist(a1);
        em.persist(a2);
        
        Long count = dao.countAll();
        assertEquals(2, count);
    }
    
    //remove
    @Test
    public void remove_emptyDB_noThrows(){
        //arrange
        Artist a1 = Generator.generateArtist();
        
        //act
        dao.remove(a1);
        List<Artist> expResult = dao.findAll();
        //assert
        assertTrue(expResult.isEmpty());
    }
    
    @Test
    public void remove_nullEntity_throwsEx(){
        //arrange
        //act
        //assert
        assertThrows(NullPointerException.class,() -> dao.remove(null));
    }
    
    @Test
    public void remove_entityInDb_emptyDb(){
        //arrange
        Artist a1 = Generator.generateArtist();
        dao.persist(a1);
        List<Artist> expResult = dao.findAll();
        assertFalse(expResult.isEmpty());
        //act
        dao.remove(a1);
        expResult = dao.findAll();
        //assert
        assertTrue(expResult.isEmpty());
    }
    
    @Test
    public void update_emptyDB_noThrowUpdatePersisted(){
        //arrange
        Artist a1 = Generator.generateArtist();
        assertTrue(dao.findAll().isEmpty());
        //act
        dao.update(a1);
        //assert
        assertFalse(dao.findAll().isEmpty());
    }
    
    @Test
    public void update_nullEntity_throwsEx(){
        //arrange
        //act;
        //assert
        assertThrows(NullPointerException.class, ()-> dao.update(null));
    }
    
    @Test
    public void update_entityInDb_entityIsUpdated(){
        //arrange
        Artist a1 = Generator.generateArtist();
        dao.persist(a1);
        a1.setBio("test test");
        List<Artist> expResult = new ArrayList<>();
        expResult.add(a1);
        //act
        dao.update(a1);
        List<Artist> result = dao.findAll();
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        //assert
        assertEquals(expResult,result);
    }
}
