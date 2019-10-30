/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.Controllers;

import com.heros.heroSightings.daos.CharacterDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.heros.heroSightings.dtos.Character;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Roshna
 */
@Controller
public class CharacterController {

    @Autowired
    CharacterDAO characterDao;

    @GetMapping("character")
    public String displayCharacters(Model model) {

        List<Character> characters = characterDao.getAllCharacters();
        
        model.addAttribute("characters", characters);

        return "character";

    }

    @PostMapping("addCharacter")
    public String addCharacter(HttpServletRequest request) {
        
        
       
        String characterName = request.getParameter("name");
        String characterDescription = request.getParameter("description");
        String characterType = request.getParameter("isHero");
        
        
      
        System.out.println(characterType);
        Character character = new Character();
        
        
        
        character.setName(characterName);
        character.setDescription(characterDescription);
        character.setIsHero(Boolean.parseBoolean(characterType));

        characterDao.addCharacter(character);

        return "redirect:/character";
    }

    @GetMapping("deleteCharacter")
    public String deleteCharacter(Integer id) {
        characterDao.deleteCharacterById(id);
        return "redirect:/character";
    }

    @GetMapping("editCharacter")
    public String editCharacter(Integer id, Model model) {
        Character character = characterDao.getCharacterById(id);
        model.addAttribute("character", character);
        return "editCharacter";
    }

    @PostMapping("editCharacter")
    public String performEditCharacter(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Character character = characterDao.getCharacterById(id);

        character.setName(request.getParameter("name"));
        character.setDescription(request.getParameter("description"));

        character.setIsHero(Boolean.parseBoolean(request.getParameter("isHero")));
        System.out.println(request.getParameter("isHero"));  
        characterDao.updateCharacter(character);

        return "redirect:/character";
    }
}
