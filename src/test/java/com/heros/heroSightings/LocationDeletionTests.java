package com.heros.heroSightings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LocationDeletionTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    public void referencedLocationIsPreservedAndExplained() throws Exception {
        jdbc.update("INSERT INTO characters(CharacterID, name, description, isHero) VALUES(901011, 'Test Hero', 'Test only', true)");
        jdbc.update("INSERT INTO Location(LocationID, LocationName, LocationAddress) VALUES(901012, 'Bahamas Test', 'Local only')");
        jdbc.update("INSERT INTO Sightings(SightingsID, CharacterID, LocationID, timeOfSighting) VALUES(901013, 901011, 901012, '2026-11-04 10:00:00')");

        mvc.perform(post("/deleteLocation").param("id", "901012"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/locations"))
                .andExpect(flash().attribute("error",
                        containsString("cannot be deleted")));

        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM Location WHERE LocationID=901012", Integer.class);
        assertEquals(Integer.valueOf(1), count);
    }

    @Test
    public void unreferencedLocationCanBeDeleted() throws Exception {
        jdbc.update("INSERT INTO Location(LocationID, LocationName, LocationAddress) VALUES(901022, 'Unused Test', 'Local only')");

        mvc.perform(post("/deleteLocation").param("id", "901022"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/locations"))
                .andExpect(flash().attribute("notice", "Location deleted."));

        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM Location WHERE LocationID=901022", Integer.class);
        assertEquals(Integer.valueOf(0), count);
    }
}
