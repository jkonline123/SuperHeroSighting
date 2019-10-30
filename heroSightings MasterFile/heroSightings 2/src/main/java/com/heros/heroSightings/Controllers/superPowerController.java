/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.Controllers;

import com.heros.heroSightings.daos.PowerDAO;
import com.heros.heroSightings.dtos.Power;
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
public class superPowerController {
    
 @Autowired 
 PowerDAO powerDao;
 
 

 @GetMapping("powers")
 public String displayPowers(Model model){
     
     List<Power>powers = powerDao.readAllPowers();
     model.addAttribute("powers", powers);
     
     return "powers";
     
 }
 @PostMapping("addPower")
    public String addPower(HttpServletRequest request){
        String powerName = request.getParameter("powerName");
        String powerDescription = request.getParameter("powerDescription");
        
        Power power = new Power();
        power.setPowerName(powerName);
        power.setPowerDescription(powerDescription);
        
        powerDao.addPower(power);
        
        return "redirect:/powers";
    }
    
    @GetMapping("deletePower")
    public String deletePower(Integer id){
        powerDao.deletePowerById(id);
        
        return "redirect:/powers";
    }
    
    
    @GetMapping("editPowers")
    public String editPower(Integer id, Model model){
        
        Power power = powerDao.readPowerById(id);
        
        
        model.addAttribute("power", power);
        return "editPowers";
    }
    
    @PostMapping("editPowers")
    public String performEditPower(HttpServletRequest request){
      int id = Integer.parseInt(request.getParameter("id"));
      Power power = powerDao.readPowerById(id);
      
      power.setPowerName(request.getParameter("powerName"));
      power.setPowerDescription(request.getParameter("powerDescription"));
      
      powerDao.updatePower(power);
      
      return "redirect:/powers";
    }
    
}
