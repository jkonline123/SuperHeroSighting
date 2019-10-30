/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.Controllers;

import com.heros.heroSightings.daos.LocationDAO;
import com.heros.heroSightings.dtos.Location;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Roshna
 */
@Controller
public class locationController {
   
    @Autowired
    LocationDAO locationDao;
    
    @GetMapping("locations")
    public String displayLocations(Model model){
        
        
        List<Location>locations =locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        
//        System.out.print("SIZE" + locations.size());


        return "location";
    }
    
    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request){
        String locationName = request.getParameter("locationName");
        String locationAddress = request.getParameter("locationAddress");
        
        Location location = new Location();
        location.setLocationName(locationName);
        location.setLocationAddress(locationAddress);
        
        locationDao.addLocation(location);
        
        return"redirect:/locations";
    }
    
    
    //DELETE 
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id){
        
        
//        int id = Integer.parseInt(request.getParameter("id"));
        
        locationDao.deleteLocationById(id);
        
        return "redirect:/locations";
    }
    
    
    
    
    //edit 
    @GetMapping("editLocation")
    public String editLocation(Integer id, Model model){
        
//        
//        int id = Integer.parseInt(request.getParameter("id"));


        Location location = locationDao.getLocationById(id);
        
        model.addAttribute("location", location);
        
        return "editLocation";
    }
    

    
    
    
    @PostMapping("editLocation")
    
    public String performEditLocation(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocationById(id);
        
        location.setLocationName(request.getParameter("locationName"));
        location.setLocationAddress(request.getParameter("locationAddress"));
        
        locationDao.updateLocation(location);
        
        return "redirect:/locations";
    }
}
