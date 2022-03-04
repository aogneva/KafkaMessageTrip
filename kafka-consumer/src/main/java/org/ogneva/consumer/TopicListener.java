package org.ogneva.consumer;

import org.ogneva.consumer.service.PersonService;
import org.ogneva.PersonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

    @Value("${topic.name.producer}")
    private String topicName;

    PersonService personService;

    @Autowired
    public TopicListener(PersonService personService) {
        this.personService = personService;
    }

    @KafkaListener(topics = "${topic.name.producer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, PersonDTO> payload) {
        log.info("Topic: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partition: {}", payload.partition());
        PersonDTO person = payload.value();
        log.info("Person: {} year: {}", person.getName(), person.getYear());

        personService.save(person);

    }
}
