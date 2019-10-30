/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;
import com.heros.heroSightings.dtos.Character;
import java.util.List;

/**
 *
 * @author Roshna
 */
public interface CharacterDAO {
    Character getCharacterById(int id);
    List<Character>getAllCharacters();
    Character addCharacter(Character character);
    void updateCharacter(Character character);
    void deleteCharacterById(int id);
    
   
    
}
