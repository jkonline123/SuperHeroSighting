/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import com.heros.heroSightings.dtos.Location;
import java.util.List;




/**
 *
 * @author triplexlj
 */
public interface LocationDAO {
    
   Location getLocationById(int id);
   List<Location>getAllLocations();
   Location addLocation(Location location);
   void updateLocation(Location location);
   void deleteLocationById(int id);
    
}
