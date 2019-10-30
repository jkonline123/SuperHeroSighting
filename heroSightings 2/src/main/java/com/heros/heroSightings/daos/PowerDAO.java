/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import com.heros.heroSightings.dtos.Power;
import java.util.List;

/**
 *
 * @author Roshna
 */
public interface PowerDAO {
    
    //Create 
   Power addPower(Power p);
    
    //READ ALL
   
   List<Power>readAllPowers();
   Power readPowerById(int id);
   void updatePower(Power p);
   void deletePowerById(int id);
   
   

    
    
}