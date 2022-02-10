/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import static cz.cvut.kbss.ear.zpevnik.constants.SecurityConstants.*;

/**
 *
 * @author Adam Škarda
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(HOME_URI.getStringVal()).setViewName("index");
		registry.addViewController(REGISTER_URI.getStringVal()).setViewName("register");
                registry.addViewController("/songs").setViewName("songsList");
    }
    
    
}