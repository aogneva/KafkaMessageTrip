package org.ogneva.consumer;

import org.ogneva.consumer.service.PersonService;
import org.ogneva.PersonDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TopicListenerSpyTest {

    @SpyBean
    PersonService personService;

    @Autowired
    TopicListener topicListener;

    @Test
    void consume() {
        doReturn(Collections.emptyList()).when(personService).findAll();
        doReturn(new PersonDTO(54L, "Masha", 2015)).when(personService).save(any());

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