/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.Controllers;

import com.heros.heroSightings.daos.CharacterDAO;
import com.heros.heroSightings.daos.LocationDAO;
import com.heros.heroSightings.daos.SightingDAO;
import com.heros.heroSightings.dtos.Sighting;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.heros.heroSightings.dtos.Character;
import com.heros.heroSightings.dtos.Location;
/**
 *
 * @author triplexlj
 */
    @Controller
public class SightingController {
    
    @Autowired
    SightingDAO sightingDao;
    @Autowired
    LocationDAO locationDao;
    @Autowired
    CharacterDAO characterDao;
    
    @GetMapping("sightings")
    public String displaySightings(Model model){
        List<Sighting> sightings = sightingDao.getAllSightings();
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locationDao.getAllLocations());
        model.addAttribute("heroes", characterDao.getAllCharacters());
        
        return "sightings";
    }
    
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request){
        String timeStamp = request.getParameter("date");
        
        Sighting sighting = new Sighting();
        sighting.setTimeStamp(timeStamp);
        
        Character character = new Character();
        character.setId(Integer.parseInt(request.getParameter("superheroID")));//this parameter relys on whats in my thymeleaf
        
        Location location = new Location();
        location.setId(Integer.parseInt(request.getParameter("locationID")));
        sighting.setCharacter(character);
        sighting.setLocation(location);
        
        sightingDao.addSighting(sighting);
        
        return "redirect:/sightings";
    }
    
    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model){
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("locations", locationDao.getAllLocations());
        model.addAttribute("heroes", characterDao.getAllCharacters());
        model.addAttribute("id", id);
        return "editSighting";
    }
    
    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id){
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }
    
    @PostMapping("editSightings")
    public String performEditOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        Sighting sighting = new Sighting();
        sighting.setSightingID(id);
        Character c = new Character();
        c.setId(Integer.parseInt(request.getParameter("superheroID")));
        sighting.setCharacter(c);
        Location l = new Location();
        l.setId(Integer.parseInt(request.getParameter("locationID")));
        sighting.setLocation(l);
        sighting.setTimeStamp(request.getParameter("timeStamp"));
        sightingDao.updateSighting(sighting);
    return "redirect:/sightings";
}
    }

