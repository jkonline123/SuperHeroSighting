/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import com.heros.heroSightings.dtos.Character;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Roshna
 */
@Repository
public class CharacterDaoDB implements CharacterDAO {

    @Autowired
    JdbcTemplate jdbc;

    
    private final String INSERT_CHARACTER = "INSERT INTO characters(name, description , isHero)VALUES(?,?,?)";
    private final String DELETE_CHARACTER = "DELETE FROM characters WHERE CharacterID = ?";
    private final String GET_CHARACTER_BY_ID = "SELECT * FROM characters WHERE CharacterID = ?";
    private final String UPDATE_CHARACTER = "UPDATE characters SET name = ? , description = ? , isHero =? WHERE CharacterID = ?";
    private final String GET_ALL_CHARACTERS = "SELECT * FROM characters";

    @Override
    public Character getCharacterById(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            return jdbc.queryForObject(GET_CHARACTER_BY_ID, new CharacterMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<Character> getAllCharacters() {

        return jdbc.query(GET_ALL_CHARACTERS, new CharacterMapper());
    }

    @Override
    public Character addCharacter(Character character) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    jdbc.update(INSERT_CHARACTER, character.getName(), character.getDescription(), character.isIsHero());
    int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    character.setId(newId);
    return character;
    }

    @Override
    public void updateCharacter(Character character) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    jdbc.update(UPDATE_CHARACTER, character.getName(), character.getDescription(),character.isIsHero(), character.getId());
    }

    @Override
    public void deleteCharacterById(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    jdbc.update(DELETE_CHARACTER, id);
    }



public static final class CharacterMapper implements RowMapper<Character> {

    @Override
    public Character mapRow(ResultSet rs, int index) throws SQLException {

        Character character = new Character();
        character.setId(rs.getInt("CharacterID"));
        character.setName(rs.getString("name"));
        character.setDescription(rs.getString("description"));
        character.setIsHero(rs.getBoolean("isHero"));

        return character;
    }
}


}