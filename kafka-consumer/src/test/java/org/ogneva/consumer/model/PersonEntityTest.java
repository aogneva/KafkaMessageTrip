package org.ogneva.consumer.model;

import org.ogneva.PersonDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonEntityTest {

    @Test
    void toDto() {
        PersonEntity entity = new PersonEntity(31L, "Peter", 1948);
        PersonDTO dto = new PersonDTO(31L, "Peter", 1948);
        assertEquals(dto, entity.toDto());
        assertEquals(dto.getId(), entity.toDto().getId());
    }

    @Test
    void toEntity() {
        PersonDTO dto = new PersonDTO(31L, "Peter", 1948);
        PersonEntity entity = PersonEntity.toEntity(dto);
        assertEquals(31L, entity.getId());
        assertEquals("Peter", entity.getName());
        assertEquals(1948, entity.getYear());
    }

    @Test
    void testEquals() {
        PersonEntity entity1 = new PersonEntity(31L, "Peter", 1948);
        PersonEntity entity2 = new PersonEntity();
        entity2.setId(32L);
        entity2.setName("Peter");
        entity2.setYear(1948);
        PersonEntity entity3 = new PersonEntity(31L, "Peter 1", 1948);
        PersonEntity entity4 = new PersonEntity(31L, "Peter", 1949);
        assertEquals(entity2, entity1);
        assertNotEquals(entity3, entity1);
        assertNotEquals(entity4, entity1);
    }


}