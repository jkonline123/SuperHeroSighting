/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import com.heros.heroSightings.dtos.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LocationDaoDB implements LocationDAO {

    @Autowired
    JdbcTemplate jdbc;

    private final String DELETE_LOCATION = "DELETE FROM Location WHERE LocationID = ?";
    private final String GET_LOCATION_BY_ID = "SELECT * FROM Location WHERE LocationID = ?";
    private final String UPDATE_LOCATION = "UPDATE location SET LocationName = ? ,LocationAddress=? WHERE LocationID = ?";

    @Override
    public Location getLocationById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {

            return jdbc.queryForObject(GET_LOCATION_BY_ID, new LocationMapper(), id);

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        final String GET_ALL_LOCATIONS = "SELECT * FROM Location";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        final String INSERT_LOCATION = "INSERT INTO Location(LocationName,LocationAddress)" + "VALUES(?,?)";
        
        
        jdbc.update(INSERT_LOCATION, location.getLocationName(),location.getLocationAddress());
        
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        jdbc.update(UPDATE_LOCATION, location.getLocationName(),location.getLocationAddress(),location.getId());

    }

    @Override
    @Transactional
    public void deleteLocationById(int id) {

        jdbc.update(DELETE_LOCATION, id);

    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("LocationID"));
            location.setLocationName(rs.getString("LocationName"));
            location.setLocationAddress(rs.getString("LocationAddress"));

            return location;
        }
    }
}
