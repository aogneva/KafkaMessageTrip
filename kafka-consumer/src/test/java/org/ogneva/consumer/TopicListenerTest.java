package org.ogneva.consumer;

import org.ogneva.consumer.service.PersonService;
import org.ogneva.PersonDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integrity test
 */
@SpringBootTest
class TopicListenerTest {

    @Autowired
    PersonService personService;

    @Autowired
    TopicListener topicListener;

    @Test
    void consume() {
        List<PersonDTO> entries = personService.findAll();
        int oldSize = entries.size();
        PersonDTO expectedPersonDTO = new PersonDTO(1L, "Masha", 2015);

        topicListener.consume(new ConsumerRecord<>(
                        "${topic.name.producer}", 0, 0, "${topic.name.producer}",
                        new PersonDTO(null, "Masha", 2015)
                )
        );

        assertTrue(personService.findAll().size() > oldSize);
        assertEquals(expectedPersonDTO, personService.findAll().get(0));
    }
}