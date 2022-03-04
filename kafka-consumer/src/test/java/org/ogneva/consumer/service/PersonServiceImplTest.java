package org.ogneva.consumer.service;

import org.ogneva.consumer.model.PersonEntity;
import org.ogneva.consumer.model.PersonRepository;
import org.ogneva.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceImplTest {

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Test
    void findAllEmpty() {
        when(personRepository.findAll()).thenReturn(Collections.emptyList());
        assertThat(personService.findAll()).isEmpty();
        verify(personRepository).findAll();
    }

    @Test
    void save() {
        PersonDTO expectedDTO = new PersonDTO(54L, "Masha", 1999);
        when(personRepository.save(any())).thenReturn(new PersonEntity(54L, "Masha", 1999));
        PersonDTO savedDTO = personService.save(new PersonDTO(null, "Masha", 1999));
        assertThat(savedDTO)
                .isNotNull()
                .isEqualTo(expectedDTO);
        verify(personRepository).save(any());
    }

    @Test
    void findById() {
        PersonDTO expectedDTO = new PersonDTO(54L, "Masha", 1999);
        PersonEntity entityToFind = new PersonEntity(54L, "Masha", 1999);
        when(personRepository.findById(any())).thenReturn(Optional.of(entityToFind));
        Optional<PersonDTO> foundDTO = personService.findById(54L);
        assertThat(foundDTO)
                .isNotNull()
                .isOfAnyClassIn(Optional.class);
        assertEquals(expectedDTO, foundDTO.get());
        verify(personRepository).findById(any());
    }

    @Test
    void findByName() {
        List<PersonDTO> expectedDTOs = List.of(
            new PersonDTO(53L, "Masha", 1962),
            new PersonDTO(54L, "Masha", 1999)
        );
        PersonEntity entityToFind1 = new PersonEntity(53L, "Masha", 1962);
        PersonEntity entityToFind2 = new PersonEntity(54L, "Masha", 1999);
        when(personRepository.findByName(any())).thenReturn(List.of(entityToFind1, entityToFind2));
        List<PersonDTO> foundDTOs = personService.findByName("Masha");
        assertThat(foundDTOs)
                .isNotNull()
                .hasSize(2)
                .extracting(PersonDTO::getName)
                .anyMatch(name -> ("Masha").equals(name) );
        verify(personRepository).findByName(any());
    }

    @Test
    void findByYear() {
        List<PersonDTO> expectedDTOs = List.of(
                new PersonDTO(54L, "Masha", 1999),
                new PersonDTO(55L, "Denis", 1999)
        );
        PersonEntity entityToFind1 = new PersonEntity(54L, "Masha", 1999);
        PersonEntity entityToFind2 = new PersonEntity(55L, "Denis", 1999);
        when(personRepository.findByYear(anyInt())).thenReturn(List.of(entityToFind1, entityToFind2));
        List<PersonDTO> foundDTOs = personService.findByYear(1999);
        assertThat(foundDTOs)
                .isNotNull()
                .hasSize(2)
                .extracting(PersonDTO::getYear)
                .anyMatch(year -> 1999 == year );
        verify(personRepository).findByYear(anyInt());
    }

    @Test
    void deleteByYear() {
        when(personRepository.deleteAllByYear(1999)).thenReturn(9L);
        Long deletedCount = personService.deleteByYear(1999);
        assertEquals(9L, deletedCount);
        verify(personRepository).deleteAllByYear(anyInt());
    }

}