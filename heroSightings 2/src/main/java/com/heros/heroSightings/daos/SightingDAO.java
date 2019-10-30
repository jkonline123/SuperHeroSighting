/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import com.heros.heroSightings.dtos.Sighting;
import java.util.List;

/**
 *
 * @author triplexlj
 */
public interface SightingDAO {
    
    public Sighting getSightingById(int id);
    
    public List<Sighting> getAllSightings();
    
    public Sighting addSighting(Sighting sighting);
    
    public void updateSighting(Sighting sighting);
     
    public void deleteSightingById(int id); 
    
    public List<Sighting> showRecentTen();
    
}
