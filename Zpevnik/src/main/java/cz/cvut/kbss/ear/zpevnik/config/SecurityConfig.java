/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.config;

import static cz.cvut.kbss.ear.zpevnik.constants.SecurityConstants.*;
import cz.cvut.kbss.ear.zpevnik.security.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Adam Škarda
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthenticationProvider authProvider;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
                    .authorizeRequests()
                        .antMatchers(HOME_URI.getStringVal(), REGISTER_URI.getStringVal(),
                                "/songs/**",
                                "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage(HOME_URI.getStringVal())
                        .loginProcessingUrl(SECURITY_CHECK_URI.getStringVal())
                        .usernameParameter(USERNAME_PARAM.getStringVal())
                        .passwordParameter(PASSWORD_PARAM.getStringVal())
                        .permitAll()
                        .defaultSuccessUrl(HOME_URI.getStringVal())
                        .and()
                    .authenticationProvider(authProvider)
                        //default csrf
                    .logout()
                        .logoutUrl(LOGOUT_URI.getStringVal())
                        .logoutSuccessUrl(HOME_URI.getStringVal())
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        .and()
                    .httpBasic();
	}

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authProvider);
        }
        
}   
