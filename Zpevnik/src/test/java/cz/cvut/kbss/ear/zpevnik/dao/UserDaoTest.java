/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import cz.cvut.kbss.ear.zpevnik.ZpevnikApp;
import cz.cvut.kbss.ear.zpevnik.model.RegularUser;
import cz.cvut.kbss.ear.zpevnik.model.User;
import cz.cvut.kbss.ear.zpevnik.model.VerifiedUser;
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
public class UserDaoTest {
    
    @Autowired
    private TestEntityManager em;
    
    @Autowired
    private UserDao dao;
    

    @Test
    public void findByUsername_ExistsInDB_returnsCorrectUser() {
        //arrange
        User expResult = Generator.generateRegularUser();
        em.persist(expResult);

        //act
        User result = dao.findByUsername(expResult.getUsername());
        
        //assert
        assertEquals(expResult.getId(), result.getId());  
        assertEquals(expResult.getClass(), result.getClass());
    }
    
    @Test
    public void findByUsername_UserIsNotInDB_returnsNull(){
        //arrange
        //act
        User result = dao.findByUsername("knedlík");
        //assert
        assertNull(result);
    }
    
    @Test
    public void  findByEmail_1VerifiedUser_returnsVerifiedUser(){
        //arrange
        VerifiedUser ver = Generator.generateVerifiedUser();
        String email = "email@me.cz";
        ver.setEmail(email);
        
        em.persist(ver);
        
        User regUser = Generator.generateRegularUser();
        em.persist(regUser);
        regUser = Generator.generateRegularUser();
        em.persist(regUser);
        regUser = Generator.generateRegularUser();
        em.persist(regUser);
        //act
        User result = dao.findByEmail(email);
        
        //assert
        assertNotNull(result);
        assertEquals(ver.getId(), result.getId());
    }
    
    @Test
    public void findByEmail_emptyDB_returnsNull(){
        //arrange
        //act
        User result = dao.findByEmail("user@domain.dom");
        //assert
        assertNull(result);
    }
    
    @Test
    public void findByEmail_noVerifiedUserDBNotEmpty_returnsNull(){
        //arrange
        User regUser = Generator.generateRegularUser();
        em.persist(regUser);
        regUser = Generator.generateRegularUser();
        em.persist(regUser);
        regUser = Generator.generateRegularUser();
        em.persist(regUser);
        //act
        User result = dao.findByEmail("user@domain.dom");
        //assert
        assertNull(result);
    }
    
    @Test
    public void findAllVerified_VerifiedInDb_returnsList(){
        //arrange
        List<VerifiedUser> expResult = new ArrayList<>();
        VerifiedUser regUser = Generator.generateVerifiedUser();
        expResult.add(em.persist(regUser));
        regUser = Generator.generateVerifiedUser();
        expResult.add(em.persist(regUser));
        regUser = Generator.generateVerifiedUser();
        expResult.add(em.persist(regUser));
        //act
        List<VerifiedUser> result = dao.findAllVerifiedUsers();
        //assert
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        assertEquals(expResult, result);
    }
    
    @Test
    public void findAllRegular_RegularsInDb_returnsList(){
        //arrange
        List<RegularUser> expResult = new ArrayList<>();
        RegularUser regUser = Generator.generateRegularUser();
        em.persist(regUser);
        expResult.add(regUser);
        
        regUser = Generator.generateRegularUser();
        em.persist(regUser);
        expResult.add(regUser);
        //act
        List<RegularUser> result = dao.findAllRegularUsers();
        result.sort((i,j) -> i.getId() - j.getId());
        expResult.sort((i,j) -> i.getId() - j.getId());
        //assert
        assertEquals(expResult, result);
    }
    
}
