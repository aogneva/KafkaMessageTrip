package org.ogneva.consumer.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Optional<PersonEntity> findById(int i);

    List<PersonEntity> findByName(String name);

    List<PersonEntity> findByYear(int year);

    Long deleteAllByYear(int year);

}
