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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EditPageSmokeTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    public void characterEditFormRenders() throws Exception {
        jdbc.update("INSERT INTO characters(CharacterID, name, description, isHero) VALUES(900001, 'Demo Hero', 'Local test', true)");
        mvc.perform(get("/editCharacter").param("id", "900001"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Save changes")));
    }

    @Test
    public void locationEditFormRenders() throws Exception {
        jdbc.update("INSERT INTO Location(LocationID, LocationName, LocationAddress) VALUES(900002, 'Test Place', 'Local test')");
        mvc.perform(get("/editLocation").param("id", "900002"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Save changes")));
    }

    @Test
    public void organizationEditFormRenders() throws Exception {
        jdbc.update("INSERT INTO organizations(organizationID, name, description, address, contactInfo) VALUES(900003, 'Test Group', 'Local test', 'Somewhere', 'Test contact')");
        mvc.perform(get("/editOrganizations").param("id", "900003"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Save changes")));
    }

    @Test
    public void powerEditFormRendersWithEditEndpoint() throws Exception {
        jdbc.update("INSERT INTO superPower(superPowerID, name, description) VALUES(900004, 'Flight', 'Fly')");
        mvc.perform(get("/editPowers").param("id", "900004"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("/editPowers")));
    }

    @Test
    public void sightingEditFormRendersWithSelectedRecords() throws Exception {
        jdbc.update("INSERT INTO characters(CharacterID, name, description, isHero) VALUES(900011, 'Demo Hero', 'Local test', true)");
        jdbc.update("INSERT INTO Location(LocationID, LocationName, LocationAddress) VALUES(900012, 'Test Place', 'Local test')");
        jdbc.update("INSERT INTO Sightings(SightingsID, CharacterID, LocationID, timeOfSighting) VALUES(900013, 900011, 900012, '2026-11-04 11:00:00')");
        mvc.perform(get("/editSighting").param("id", "900013"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Save changes")));
    }
}
