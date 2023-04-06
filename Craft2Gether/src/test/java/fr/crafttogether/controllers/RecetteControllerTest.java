package fr.crafttogether.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.crafttogether.UnitTestsBase;
import fr.crafttogether.models.Recette;
import fr.crafttogether.services.RecetteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
class RecetteControllerTest extends UnitTestsBase {

    @MockBean
    RecetteService recetteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        reset(recetteService);
    }

    @Test
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
}