/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import com.heros.heroSightings.dtos.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDAODB implements LocationDAO {
  private final JdbcTemplate jdbc;

  private final String INSERT_LOCATION = "INSERT INTO Location(LocationName, LocationAddress, Latitude, Longitude)" + "VALUES(?,?,?,?)";
  
    @Autowired
     public LocationDAODB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Location createLocation(Location L) {
        
    jdbc.update(INSERT_LOCATION, L.getLocationName(),L.getLocationAddress(),L.getLatitude(),L.getLongitude());
    
    int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    
    L.setLocationID(newId);
    
    return L;

    }

   public static final class LocationMapper implements org.springframework.jdbc.core.RowMapper<Location>{//rowMapper turns a row into a java object 

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location L = new Location();
            
            L.setLocationID(rs.getInt(""));
            L.setLocationName(rs.getString(""));
            L.setLocationAddress(rs.getString(""));
            L.setLatitude(rs.getFloat(""));
            L.setLongitude(rs.getFloat(""));
            
            return L;
        }
       
   }
    
}
