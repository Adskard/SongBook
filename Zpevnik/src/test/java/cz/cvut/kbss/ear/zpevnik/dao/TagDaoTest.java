/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import cz.cvut.kbss.ear.zpevnik.ZpevnikApp;
import cz.cvut.kbss.ear.zpevnik.model.Tag;
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

/**
 *
 * @author Adam Škarda
 */
@DataJpaTest
@ComponentScan(basePackageClasses = ZpevnikApp.class,excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SystemInitializer.class)})
public class TagDaoTest {
    @Autowired
    private TestEntityManager em;
    
    @Autowired
    private TagDao dao;
    
    @Test
    public void findByName_distinctTags_returnsOneTag(){
        //arrange
        List<Tag> expResult = new ArrayList<>();
        Tag t = new Tag();
        t.setName("Country");
        em.persist(t);
        
        t = new Tag();
        t.setName("Rock");
        expResult.add(t);
        em.persist(t);
        //act
        List<Tag> result = dao.findByName("Rock");
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        //assert
        assertEquals(expResult, result);
    }
    
    @Test
    public void findByName_similarTags_returnsBothTags(){
        //arrange
        List<Tag> expResult = new ArrayList<>();
        Tag t = new Tag();
        t.setName("Progressive Rock");
        em.persist(t);
        expResult.add(t);
        
        t = new Tag();
        t.setName("Rock");
        expResult.add(t);
        em.persist(t);
        //act
        List<Tag> result = dao.findByName("Rock");
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        //assert
        assertEquals(expResult, result);
    }
    
    @Test
    public void findByName_emptyDB_returnsEmptyList(){
        //arrange
        //act
        List<Tag> result = dao.findByName("Hroch");
        
        //assert
        assertTrue(result.isEmpty());
    }
}
