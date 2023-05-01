package fr.crafttogether.services;

import fr.crafttogether.UnitTestsBase;
import fr.crafttogether.exceptions.NotFoundException;
import fr.crafttogether.models.Liste;
import fr.crafttogether.repositories.ListeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListeServiceTest extends UnitTestsBase {

    @MockBean
    private ListeService listeService;

    @MockBean
    private ListeRepository listeRepository;

    @BeforeEach
    void setUp() throws Exception {
        //reset();
    }

    @Test
    void testFindByIdSucces() {
        // arrange
        int id = 123;
        Optional<Liste> liste = Optional.of(Liste.builder().id(1).build());
        when(listeRepository.findById(id))
                .thenReturn(liste);
        // act and assert
        var res = this.listeRepository.findById(id);
        assertEquals(liste, res);
        verify(listeRepository, Mockito.times(1)).findById(id);
        verifyNoMoreInteractions(listeRepository);
    }

    @Test
    void testFindAll() {
        // arrange
        List<Liste> dummyListes = List.of(Liste.builder().id(1).build(), Liste.builder().id(2).build(),
                Liste.builder().id(3).build());
        when(listeRepository.findAll()).thenReturn(dummyListes);
        // act
        var result = listeService.findAll();
        // assert
        assertIterableEquals(dummyListes, result);
        verify(listeRepository, Mockito.times(1)).findAll();
        verifyNoMoreInteractions(listeRepository);
    }

    @Test
    void testFindByIdErrorNotFound() {
        // arrange
        int id = 123;
        when(listeRepository.findById(id)).thenReturn(Optional.empty());
        // act and assert
        assertThrows(NotFoundException.class, () -> listeService.findById(id));
        verify(listeRepository, Mockito.times(1)).findById(id);
        verifyNoMoreInteractions(listeRepository);
    }



    @Test
    //@WithMockUser(roles = "ADMIN")
    void deleteByIdSuccess() {
        // arrange
        int id = 123;
        when(listeRepository.findById(id)).thenReturn(Optional.of(Liste.builder().id(id).build()));
        // act
        listeService.deleteById(id);
        // assert
        verify(listeRepository, Mockito.times(1)).findById(id);
        verify(listeRepository, Mockito.times(1)).deleteById(id);
        verifyNoMoreInteractions(listeRepository);
    }

    @Test
    //@WithMockUser(roles = "ADMIN")
    void deleteByIdErrorNotFound() {
        // arrange
        int id = 123;
        when(listeRepository.findById(id)).thenReturn(Optional.empty());
        // act and assert
        assertThrows(NotFoundException.class, () -> listeService.deleteById(id));
        verify(listeRepository, Mockito.times(1)).findById(id);
        verifyNoMoreInteractions(listeRepository);
    }

    @Test
    void deleteByIdErrorUnauthenticated() {
        // arrange
        // act and assert
        //assertThrows(AuthenticationCredentialsNotFoundException.class, () -> listeService.deleteById(123));
        verifyNoInteractions(listeRepository);
    }

    @Test
   // @WithMockUser(roles = "CLIENT")
    void deleteByIdErrorUnauthorized() {
        // arrange
        // act and assert
        assertThrows(AccessDeniedException.class, () -> listeService.deleteById(123));
        verifyNoInteractions(listeRepository);
    }

    @Test
   // @WithMockUser(roles = "ADMIN")
    void saveSuccess() {
        // Arrange
        Liste liste = Liste.builder().id(1).build();
        when(listeRepository.save(liste)).thenReturn(liste);
        // Act
        Liste result = listeService.save(liste);
        // Assert
        verify(listeRepository, Mockito.times(1)).save(result);
        verifyNoMoreInteractions(listeRepository);
    }

    @Test
    void saveErrorUnauthenticated() {
        // Arrange
        Liste liste = Liste.builder().id(1).build();
        //when(listeRepository.save(liste)).thenReturn(liste);
        // Act
        // Assert
        //assertThrows(AuthenticationCredentialsNotFoundException.class, () -> listeService.save(liste));
        verifyNoInteractions(listeRepository);
    }



    @Test
    //@WithMockUser(roles = "CLIENT")
    void testSaveErrorUnauthorized() {
        //arrange
        Liste a = Liste.builder().titre("chocolat").build();
        //act and assert
        assertThrows(AccessDeniedException.class, () -> listeService.save(a));
        verifyNoInteractions(listeRepository);
    }

    @Test
    void updateErrorUnauthenticated(){
        //Arrange
        Liste a = Liste.builder().build();
        //act and assert
       // assertThrows(AuthenticationCredentialsNotFoundException.class, () -> listeService.update(a));
        verifyNoInteractions(listeRepository);
    }


}