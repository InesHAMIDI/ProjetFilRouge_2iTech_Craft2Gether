package fr.crafttogether.controllers;

import fr.crafttogether.IntegrationTestsBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
public class ListeControllerTestIT extends IntegrationTestsBase {

    @Autowired
    private MockMvc mockMvc;


    /* TEST GET LISTES*/

    @Test
    @WithMockUser(roles = {"ADMIN", "PLAYER"})
    void testGetListesSuccess() throws Exception {
        // Arrange
        // Act & Assert
        mockMvc.perform(get("/listes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[4].titre", is("un nouvel ecran")));
    }

    @Test
    void testGetListesErrorUnauthorized() throws Exception{
        mockMvc.perform(get("/listes"))
                .andExpect(status().isUnauthorized());
    }

    // TESTS GET BY ID
    @Test
    @WithMockUser(roles = "PLAYER")
    void testGetListeByIdSuccess() throws Exception {
        // Arrange
        // Act & Assert
        mockMvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.titre", is("un nouvel ecran")));
    }
}
