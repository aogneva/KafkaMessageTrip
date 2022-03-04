package org.ogneva.consumer.service;

import org.ogneva.consumer.model.PersonEntity;
import org.ogneva.consumer.model.PersonRepository;
import org.ogneva.PersonDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    PersonServiceImpl(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream().map(PersonEntity::toDto).collect(Collectors.toList());
    }

    public PersonDTO save(PersonDTO personDTO)  {
        PersonEntity entity = PersonEntity.toEntity(personDTO);
        PersonEntity savedEntity = personRepository.save(entity);
        PersonDTO result = savedEntity!= null ? savedEntity.toDto() : null;
        return result;
    }

    public Optional<PersonDTO> findById(Long id) {
        return personRepository.findById(id).map(PersonEntity::toDto);
    }

    public List<PersonDTO> findByName(String name)  {
        return personRepository.findByName(name).stream().map(PersonEntity::toDto).collect(Collectors.toList());
    }
    public List<PersonDTO> findByYear(int year)  {
        return personRepository.findByYear(year).stream().map(PersonEntity::toDto).collect(Collectors.toList());
    }

    public Long deleteByYear(int year)  {
        return personRepository.deleteAllByYear(year);
    }
}
