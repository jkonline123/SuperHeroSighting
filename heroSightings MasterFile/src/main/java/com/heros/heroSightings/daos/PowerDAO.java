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
 * @author triplexlj
 */
public interface PowerDAO {
    
   //CREATE
   Power addPower(Power power);
   
   //ReadAll
   List<Power> readAllPowers();
   
   //ReadById
   Power readById(int id);
   
   //Delete
   void deletePowers(int id);
}
