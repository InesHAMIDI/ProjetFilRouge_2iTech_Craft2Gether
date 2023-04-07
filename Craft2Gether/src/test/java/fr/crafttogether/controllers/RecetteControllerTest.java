package fr.crafttogether.controllers;

import fr.crafttogether.UnitTestsBase;
import fr.crafttogether.models.Recette;
import fr.crafttogether.services.RecetteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class RecetteControllerTest extends UnitTestsBase {

    @MockBean
    private RecetteService recetteService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        reset(recetteService);
    }

    @Test
    //@WithMockUser(roles = "PLAYER")
    void testGetRecettesSuccess() throws Exception{
        // Arrange
        List<Recette> dummyRecettes = List.of(
                Recette.builder().id(1).build(),
                Recette.builder().id(2).build(),
                Recette.builder().id(3).nom("chocolat").build()
                );
        when(recetteService.findAll()).thenReturn(dummyRecettes);
        // Act & Assert
        mockMvc.perform(get("/recettes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].nom", is("chocolat")));
        verify(recetteService, times(1)).findAll();
        verifyNoMoreInteractions(recetteService);
    }

    @Test
    //@WithMockUser(roles = "PLAYER")
    void testGetRecetteByIdSuccess() throws Exception{
        // Arrange
        int id = 3;
        Recette dummyRecette = Recette.builder().id(id).nom("chocolat").build();
        when(recetteService.findById(id)).thenReturn(dummyRecette);
        // Act & Assert
        mockMvc.perform(get("/recettes/i/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("nom", is("chocolat")));
        verify(recetteService, times(1)).findById(id);
        verifyNoMoreInteractions(recetteService);
    }

    @Test
    //@WithMockUser(roles = "PLAYER")
    void testGetRecetteByNomSuccess() throws Exception{
        // Arrange
        Recette dummyRecette = Recette.builder().id(3).nom("chocolat").build();
        when(recetteService.findByNom("chocolat")).thenReturn(dummyRecette);
        // Act & Assert
        mockMvc.perform(get("/recettes/n/" + "chocolat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(3)));
        verify(recetteService, times(1)).findByNom("chocolat");
        verifyNoMoreInteractions(recetteService);
    }
}