package org.ogneva.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ogneva.PersonDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<Object, PersonDTO> kafkaTemplate;

    public void sendPerson(String name, int year) {
        log.info("Person name: {} year: {}", name, year);
        kafkaTemplate.send(topicName, new PersonDTO(null, name, year));
    }
}
