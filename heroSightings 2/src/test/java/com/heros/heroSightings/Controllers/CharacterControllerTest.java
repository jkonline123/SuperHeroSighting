package com.heros.heroSightings.Controllers;

import com.heros.heroSightings.daos.CharacterDAO;
import com.heros.heroSightings.dtos.Character;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CharacterControllerTest {

    private CharacterDAO characterDao;
    private CharacterController controller;

    @Before
    public void setUp() {
        characterDao = mock(CharacterDAO.class);
        controller = new CharacterController(characterDao);
    }

    @Test
    public void displayCharactersAddsDaoResultsToModel() {
        Character hero = new Character();
        hero.setId(1);
        hero.setName("Example Hero");
        when(characterDao.getAllCharacters()).thenReturn(Arrays.asList(hero));

        ExtendedModelMap model = new ExtendedModelMap();

        assertEquals("character", controller.displayCharacters(model));
        assertSame(hero, ((java.util.List<?>) model.get("characters")).get(0));
        verify(characterDao).getAllCharacters();
    }

    @Test
    public void deleteCharacterDelegatesToDao() {
        assertEquals("redirect:/character", controller.deleteCharacter(42));
        verify(characterDao).deleteCharacterById(42);
    }

    @Test
    public void editCharacterAddsSelectedCharacterToModel() {
        Character hero = new Character();
        hero.setId(7);
        when(characterDao.getCharacterById(7)).thenReturn(hero);

        ExtendedModelMap model = new ExtendedModelMap();

        assertEquals("editCharacter", controller.editCharacter(7, model));
        assertSame(hero, model.get("character"));
    }
}
