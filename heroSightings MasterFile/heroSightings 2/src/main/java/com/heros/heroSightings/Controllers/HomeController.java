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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author triplexlj
 */
@Controller
public class HomeController {
    @Autowired
    SightingDAO sightingDao;
    @Autowired
    LocationDAO locationDao;
    @Autowired
    CharacterDAO characterDao;
    
    @GetMapping("/")
    public String displaySightings(Model model){
        List<Sighting> sightings = sightingDao.showRecentTen();
        for (Sighting s :sightings) {
            s.setCharacter(characterDao.getCharacterById(s.getCharacter().getId()));
            s.setLocation(locationDao.getLocationById(s.getLocation().getId()));
        }
        model.addAttribute("sightings", sightings);
        
        
        return "home";
    }
}
