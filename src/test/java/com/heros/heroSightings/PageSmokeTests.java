package com.heros.heroSightings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PageSmokeTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void homePageRenders() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void characterPageRenders() throws Exception {
        mvc.perform(get("/character"))
                .andExpect(status().isOk())
                .andExpect(view().name("character"));
    }

    @Test
    public void locationPageRenders() throws Exception {
        mvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(view().name("location"));
    }

    @Test
    public void organizationPageRenders() throws Exception {
        mvc.perform(get("/organizations"))
                .andExpect(status().isOk())
                .andExpect(view().name("organizations"));
    }

    @Test
    public void powerPageRenders() throws Exception {
        mvc.perform(get("/powers"))
                .andExpect(status().isOk())
                .andExpect(view().name("powers"));
    }

    @Test
    public void sightingPageRenders() throws Exception {
        mvc.perform(get("/sightings"))
                .andExpect(status().isOk())
                .andExpect(view().name("sightings"));
    }
}
