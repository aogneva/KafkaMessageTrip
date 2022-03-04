package org.ogneva.consumer;

import org.ogneva.consumer.service.PersonService;
import org.ogneva.PersonDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Mock test
 */
@SpringBootTest
class TopicListenerMockTest {

    @MockBean
    PersonService personService;

    @InjectMocks
    @Autowired
    TopicListener topicListener;

    @Test
    void consume() {
        when(personService.findAll()).thenReturn(Collections.emptyList());
        when(personService.save(any())).thenReturn(new PersonDTO(54L, "Masha", 2015));

        List<PersonDTO> persons = personService.findAll();
        assertEquals(0L,persons.size());

        topicListener.consume(new ConsumerRecord<>(
            "${topic.name.producer}", 0,0,"${topic.name.producer}",
                new PersonDTO(null, "Masha", 2015)
            )
        );


        verify(personService, times(1)).save(any());
        assertEquals(0, personService.findAll().size());
    }
}