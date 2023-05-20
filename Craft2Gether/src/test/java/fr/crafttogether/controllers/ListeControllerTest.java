package fr.crafttogether.controllers;

import fr.crafttogether.UnitTestsBase;
import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import fr.crafttogether.services.ListeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ListeControllerTest extends UnitTestsBase {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListeService listeService;

    @BeforeEach
    void setUp() throws Exception {
        reset(listeService);
    }

    @Test
        //@WithMockUser(roles = "PLAYER")
    void testGetListesSuccess() throws Exception{
        // Arrange
        List<Liste> dummyListes = List.of(
                Liste.builder().id(1).build(),
                Liste.builder().id(2).build(),
                Liste.builder().id(3).titre("chocolat").build()
        );
        when(listeService.findAll()).thenReturn(dummyListes);
        // Act & Assert
        mockMvc.perform(get("/listes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].titre", is("chocolat")));
        verify(listeService, times(1)).findAll();
        verifyNoMoreInteractions(listeService);
    }

    @Test
        //@WithMockUser(roles = "PLAYER")
    void testGetListesByIdSuccess() throws Exception{
        // Arrange
        int id = 3;
        Liste dummyListe = Liste.builder().id(id).titre("chocolat").build();
        when(listeService.findById(id)).thenReturn(dummyListe);
        // Act & Assert
        mockMvc.perform(get("/listes/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("titre", is("chocolat")));
        verify(listeService, times(1)).findById(id);
        verifyNoMoreInteractions(listeService);
    }

    @Test
        //@WithMockUser(roles = "PLAYER")
    void testGetListesByIdFail() throws Exception{
        // Arrange
        doThrow(new NotFoundException("element does not exist")).when(listeService).findById(45);
        // Act & Assert
        mockMvc.perform(get("/listes/" + 45))
                .andExpect(status().isNotFound());
        verify(listeService, times(1)).findById(45);
        verifyNoMoreInteractions(listeService);
    }
}