package fr.crafttogether.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.crafttogether.UnitTestsBase;
import fr.crafttogether.models.Recette;
import fr.crafttogether.models.User;
import fr.crafttogether.services.RecetteService;
import fr.crafttogether.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class UserControllerTest extends UnitTestsBase {
    @MockBean
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        reset(userService);
    }

    @Test
    void getUsersSuccess() throws Exception {
        // Arrange
        List<User> dummyUsers = List.of(
                User.builder().id(1).build(),
                User.builder().id(2).build(),
                User.builder().id(3).username("chocolat").build()
        );
        when(userService.findAll()).thenReturn(dummyUsers);
        // Act & Assert
        mockMvc.perform(get("/recettes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].nom", is("chocolat")));
        verify(userService, times(1)).findAll();
        verifyNoMoreInteractions(userService);
    }
}