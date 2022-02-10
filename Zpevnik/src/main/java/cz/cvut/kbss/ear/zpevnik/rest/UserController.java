/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.rest;

import cz.cvut.kbss.ear.zpevnik.constants.Globals;
import cz.cvut.kbss.ear.zpevnik.model.RegularUser;
import cz.cvut.kbss.ear.zpevnik.model.User;
import cz.cvut.kbss.ear.zpevnik.model.UserStatus;
import cz.cvut.kbss.ear.zpevnik.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Adam Škarda
 */
@Controller
public class UserController {
    private final UserService service;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String listUsers(Integer page, Model model, RedirectAttributes redirectAttributes){
        try{
           if(page == null){
            page = 1;
            }

            int pageNum = (int)service.countAll()/Integer.parseInt(Globals.ITEMS_PER_PAGE.getStringVal());
            if( page >= pageNum ){
                page = pageNum -1;
            }
            if(page <= 0){
                page =1;
            }
            List<User> users = new ArrayList<>( service.findAll((page-1)*Integer.parseInt(Globals.ITEMS_PER_PAGE.getStringVal()),
                    Integer.parseInt(Globals.ITEMS_PER_PAGE.getStringVal())));
            users = users.stream().filter((User u )-> !u.getType().equals("ADMIN")).collect(Collectors.toList());
            model.addAttribute("users", users);
            model.addAttribute("page", page);
            model.addAttribute("maxPages", pageNum);  
        }
        catch(Exception ex){
            model.addAttribute("error", ex.getMessage());
        }
        return "index";
    }

    @GetMapping("user/register")
    public String sendRegForm(){
        return "register";
    }
    
    @PostMapping("/user/register")
    public String registerUser(@RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false)String email,
            RedirectAttributes redirectAttributes){
        try{
            if(email == null || email.isBlank()){
                service.registerUser(username, password);
            }
            else{
                service.registerVerifiedUser(username, password, email); 
            }
            redirectAttributes.addAttribute("msg", "Registration succes! Log IN!");
        }
        catch(Exception ex){
            redirectAttributes.addAttribute("error", ex.getMessage());
            return "redirect:/user/register";
        }
        return "redirect:/";
    }
    
    @GetMapping("/user/{id}/state")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String changeUserState(@PathVariable Integer id,
            RedirectAttributes redirectAttributes){
        User foundUser = service.find(id);
        RegularUser u = (RegularUser)foundUser;
        if(u.getStatus().equals(UserStatus.DISABLED)){
            service.changeUserState(id, UserStatus.ACTIVE);
        }
        else{
            service.changeUserState(id, UserStatus.DISABLED);
        }
        log.debug("User "+u.getUsername()+" state changed to " + u.getStatus());
        redirectAttributes.addAttribute("msg", "User "+u.getUsername()+" state changed to " + u.getStatus());
        return "redirect:/";
    }
}
