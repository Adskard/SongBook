/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service;

import cz.cvut.kbss.ear.zpevnik.model.Admin;
import cz.cvut.kbss.ear.zpevnik.model.Song;
import cz.cvut.kbss.ear.zpevnik.model.VerifiedUser;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author Adam Škarda
 */
@Service
public class SystemInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(SystemInitializer.class);

    /**
     * Default admin username
     */

    private final UserService userService;
    private final SongService songService;

    private final PlatformTransactionManager txManager;
    
    private final String adminUserName = "admin1";
    private final String regUserName = "reg1";
    private final String verUserName = "ver1";
    
    @Autowired
    public SystemInitializer(UserService userService,
                             PlatformTransactionManager txManager,
                             PasswordEncoder passwordEncoder,
                            SongService songService) {
        this.userService = userService;
        this.txManager = txManager;
        this.songService = songService;
    }

    @PostConstruct
    private void initSystem() {
        TransactionTemplate txTemplate = new TransactionTemplate(txManager);
        txTemplate.execute((status) -> {
            generateAdmin();
            generateRegular();
            generateVerified();
            generateSongs();
            return null;
        });
    }

    /**
     * Generates an admin account if it does not already exist.
     */
    private void generateAdmin() {
        if (userService.usernameExists(adminUserName)) {
            return;
        }
        final Admin admin = new Admin();
        admin.setUsername(adminUserName);
        admin.setPassword(adminUserName);
        LOG.info("Generated admin user with credentials " + admin.getUsername() + "/" + admin.getPassword());
        userService.persist(admin);
    }
    
    private void generateRegular() {
        if (userService.usernameExists(regUserName)) {
            return;
        }
        LOG.info("Generated reg user with credentials " + regUserName + "/" + regUserName);
        userService.registerUser(regUserName,regUserName);
    }
    
    private void generateVerified() {
        if (userService.usernameExists(verUserName)) {
            return;
        }
        LOG.info("Generated reg user with credentials " + verUserName + "/" + verUserName +"mail: " + verUserName+ "@neco.cz" );
        userService.registerVerifiedUser(verUserName, verUserName, verUserName + "@neco.cz");
    }
    private void generateSongs(){
        for (int i = 0; i < 10; i++) {
            Song s = Generator.generateSong();
            s.setAuthor((VerifiedUser)userService.findByUsername(verUserName));
            songService.persist(Generator.generateSong());
        }
    }
}
