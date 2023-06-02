package fr.crafttogether.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.crafttogether.UnitTestsBase;
import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import fr.crafttogether.services.ListeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class ListeControllerTest extends UnitTestsBase {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListeService listeService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        reset(listeService);
    }

    /* TEST GET LISTES*/
    @Test
    @WithMockUser(roles = "PLAYER")
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
    void testGetListesErrorUnauthorized() throws Exception{
        // Arrange
		// Act
		var result = mockMvc.perform(post("/articles"));
		// Assert
		result.andExpect(status().isForbidden());
		verifyNoInteractions(listeService);
    }

    /* TEST GET BY ID */
    @Test
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
    @WithMockUser(roles = "ADMIN")
    void testGetListesByIdErrorNotFound() throws Exception{
        // Arrange
        int id = 3;
        when(listeService.findById(id)).thenThrow(NotFoundException.class);
        // Act & Assert
        mockMvc.perform(get("/listes/" + id))
                .andExpect(status().isNotFound());
        verify(listeService, times(1)).findById(id);
        verifyNoMoreInteractions(listeService);
    }

    /* TESTS DELETE */

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteListeByIdSuccess() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/listes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
        verify(this.listeService, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(this.listeService);

    }
    @Test
    void testDeleteListeByIdErrorUnauthorized() throws Exception{
        // Arrange
        // Act
        var result = mockMvc.perform(delete("/listes/1"));
        // assert
        result.andExpect(status().isForbidden());
        verifyNoInteractions(listeService);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteByIdErrorNotFound() throws Exception {
        // Arrange
        doThrow(new NotFoundException("element does not exist")).when(listeService).deleteById(123);
        // act & assert
        mockMvc.perform(delete("/listes/123"))
                .andExpect(status().isNotFound());
        verify(listeService, times(1)).deleteById(123);
        verifyNoMoreInteractions(listeService);

    }

    /* TEST SAVE */

    @Test
    @WithMockUser(roles = "ADMIN")
    void testSaveListeSuccess() throws Exception {
        // Arrange
        Liste liste = Liste.builder()
                .titre("chocolat")
                .build();
        when(listeService.save(liste)).then(invocation -> {
            liste.setId(123);
            return liste;
        });
        // Act & Assert
        var result = mockMvc.perform(post("/listes")
                .content(objectMapper.writeValueAsString(liste))
                .contentType(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(123)))
                .andExpect(jsonPath("$.titre", is("chocolat")));
        verify(listeService, times(1)).save(any(Liste.class));
        verifyNoMoreInteractions(listeService);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testSaveErrorNotValid() throws Exception {
        // arrange
        Liste liste = Liste.builder()
                .titre("")
                .id(-1)
                .build();
        // act
        var result = mockMvc.perform(post("/listes")
                .content(objectMapper.writeValueAsString(liste))
                .contentType(MediaType.APPLICATION_JSON));
        // assert
        result.andExpect(status().isBadRequest());
        verifyNoInteractions(listeService);
    }

    @Test
    void testSaveErrorUnauthorized() throws Exception {
        // arrange
        // act
        var result = mockMvc.perform(post("/articles"));
        // assert
        result.andExpect(status().isForbidden());
        verifyNoInteractions(listeService);
    }


    /* TEST UPDATE */
}