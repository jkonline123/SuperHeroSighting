/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heros.heroSightings.daos;

import com.heros.heroSightings.dtos.Location;
import com.heros.heroSightings.dtos.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roshna
 */
@Repository
public class PowerDaoDB implements PowerDAO {

    @Autowired
    JdbcTemplate jdbc;

    private final String INSERT_POWER = "INSERT INTO superPower (name,description) VALUES(?,?);";
    private final String READ_ALL_POWERS = "SELECT * FROM superPower;";
    private final String READ_POWER_BY_ID = "SELECT *  FROM superPower WHERE superPowerID = ?";
    private final String DELETE_POWER_BY_ID = "DELETE FROM superPower WHERE superPowerID = ?";
    private final String UPDATE_POWER = "UPDATE superPower SET name = ? , description = ? WHERE superPowerID = ?";

    @Override
    public Power addPower(Power p) {

        jdbc.update(INSERT_POWER, p.getPowerName(), p.getPowerDescription());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        p.setId(newId);
        return p;

    }

    @Override
    public List<Power> readAllPowers() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        final String READ_ALL_POWERS = "SELECT * FROM superPower";
        return jdbc.query(READ_ALL_POWERS, new PowerMapper());

    }

    @Override
    public Power readPowerById(int id) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            return jdbc.queryForObject(READ_POWER_BY_ID, new PowerMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deletePowerById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        jdbc.update(DELETE_POWER_BY_ID, id);
    }

    @Override
    public void updatePower(Power p) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        jdbc.update(UPDATE_POWER, p.getPowerName(), p.getPowerDescription(), p.getId());

    }

    public static final class PowerMapper implements RowMapper<Power> {
        @Override
        public Power mapRow(ResultSet rs, int index) throws SQLException {

            Power power = new Power();
            power.setId(rs.getInt("superPowerID"));
            power.setPowerName(rs.getString("name"));
            power.setPowerDescription(rs.getString("description"));

            return power;

        }
    }

}
