/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.rest;

import cz.cvut.kbss.ear.zpevnik.constants.Globals;
import cz.cvut.kbss.ear.zpevnik.exception.InvalidRequestException;
import cz.cvut.kbss.ear.zpevnik.exception.ItemNotFoundException;
import cz.cvut.kbss.ear.zpevnik.model.Song;
import cz.cvut.kbss.ear.zpevnik.model.VerifiedUser;
import cz.cvut.kbss.ear.zpevnik.security.SecurityUtils;
import cz.cvut.kbss.ear.zpevnik.service.SongService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
public class SongController {
    
    private final SongService service;
    
    private static final Logger log = LoggerFactory.getLogger(SongController.class);

    @Autowired
    public SongController(SongService service) {
        this.service = service;
    }
   
    @GetMapping(value = "/songs/popularSongs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Song> listPopSongs(){
        return service.findTopPopularSongs(12);
    }
    
    @GetMapping(value = "/songs/{id}")
    public String listSongsById(@PathVariable Integer id,
                                Model model,
                                RedirectAttributes redirect){
        try{
            Song s = service.find(id);
            
            if(s == null){
                log.debug("song " + id + " not found!");
                throw new ItemNotFoundException("No song with id: " + id + " found in DB!");
            }  
            s.setVisits(s.getVisits()+1);
            service.update(s);
            model.addAttribute("song", s);
            return "song"; 
        }
        catch(Exception ex){
            redirect.addAttribute("error", ex.getMessage());
        }
        return "redirect:/songs";         
    }
    
    @GetMapping(value = "/songs")
    public String listAllSongs(Model model, @RequestParam(required = false) Integer page){
        try{
           if(page == null){
            page = 1;
            }

            int pageNum = (int)service.countAll()/Integer.parseInt(Globals.ITEMS_PER_PAGE.getStringVal());
            if( page > pageNum ){
                page = pageNum -1;
            }
            if(page <= 0){
                page =1;
            }
            List<Song> songs = new ArrayList<>( service.findAll((page-1)*Integer.parseInt(Globals.ITEMS_PER_PAGE.getStringVal()),
                    Integer.parseInt(Globals.ITEMS_PER_PAGE.getStringVal())));
            model.addAttribute("songs", songs);
            model.addAttribute("page", page);
            model.addAttribute("maxPages", pageNum);  
        }
        catch(Exception ex){
            model.addAttribute("error", ex.getMessage());
        }
        return "songsList";
    }

    @PreAuthorize("hasRole('ROLE_VERIFIED')")
    @PostMapping("songs/addOwn")
    public String addSong(@RequestParam(name = "name") String name,
            @RequestParam(name = "text") String text,
            RedirectAttributes redirect){
        try{
            VerifiedUser current = (VerifiedUser) SecurityUtils.getCurrentUser();
            Song songToAdd = new Song();
            songToAdd.setAuthor(current);
            
            if(name.isBlank()){
                throw new InvalidRequestException("Song needs a name");
            }
            if(text.isBlank()){
                throw new InvalidRequestException("Song needs a text");
            }
            songToAdd.setText(text);
            songToAdd.setName(name);
            service.persist(songToAdd);
            redirect.addAttribute("msg", "SONG succesfully added");
            log.debug("Song added "+ songToAdd.getName());
        }
        catch(Exception ex){
            redirect.addAttribute("error", ex.getMessage());
        }  
        
        return "redirect:/songs";
    }
    
}
