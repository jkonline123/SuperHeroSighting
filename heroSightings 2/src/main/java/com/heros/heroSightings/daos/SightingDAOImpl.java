/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import com.heros.heroSightings.dtos.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.heros.heroSightings.dtos.Character;
import com.heros.heroSightings.dtos.Location;

@Repository
public class SightingDAOImpl implements SightingDAO {

    @Autowired
    JdbcTemplate jdbc;
    
    
     @Autowired
    public SightingDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    @Override
    public Sighting getSightingById(int id) {
  try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM Sightings WHERE id = ?";
            return jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }    }

    @Override
    public List<Sighting> getAllSightings() {
final String READ_ALL_SIGHTINGS = "SELECT * FROM Sightings";
        return jdbc.query(READ_ALL_SIGHTINGS, new SightingDAOImpl.SightingMapper());
    }    

    @Override
    public Sighting addSighting(Sighting sighting) {
final String INSERT_SIGHTING = "INSERT INTO Sightings(timeOfSighting, CharacterID, LocationID) VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING, sighting.getTimeStamp(), sighting.getCharacter().getId(), sighting.getLocation().getId());
        
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(newID);
        
        return sighting;
    }    

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE Sightings SET timeOfSighting = ?, CharacterID = ?, LocationID=? WHERE SightingsID = ?";
        jdbc.update(UPDATE_SIGHTING, sighting.getTimeStamp(), sighting.getCharacter().getId(), sighting.getLocation().getId(), sighting.getSightingID());    
    }
    
    @Override
    public void deleteSightingById(int id) {
 final String DELETE_SIGHTING_BY_ID = "DELETE FROM Sightings WHERE SightingsID = ?";
jdbc.update(DELETE_SIGHTING_BY_ID, id);
     }
    @Override
     public List<Sighting> showRecentTen(){
         final String SHOW_RECENT = " SELECT * FROM Sightings order by timeOfSighting DESC Limit 10";
         return jdbc.query(SHOW_RECENT, new SightingMapper());
          
         
     }
      public static final class SightingMapper implements org.springframework.jdbc.core.RowMapper<Sighting> {
    @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("SightingsID")); 
            sighting.setTimeStamp(rs.getString("timeOfSighting"));
            Character character = new Character();
            character.setId(rs.getInt("CharacterID"));
            Location location = new Location();
            location.setId(rs.getInt("LocationID"));
            sighting.setLocation(location);
            sighting.setCharacter(character);
            return sighting;
        }
}
}
