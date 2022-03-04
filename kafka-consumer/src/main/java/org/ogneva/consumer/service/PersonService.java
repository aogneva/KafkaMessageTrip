package org.ogneva.consumer.service;

import org.ogneva.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<PersonDTO> findById(Long id);
    List<PersonDTO> findAll();
    List<PersonDTO> findByName(String name);
    List<PersonDTO> findByYear(int year);
    Long deleteByYear(int year);
    PersonDTO save(PersonDTO personDTO);
}
