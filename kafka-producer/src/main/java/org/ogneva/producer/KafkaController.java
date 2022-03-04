package org.ogneva.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final TopicProducer topicProducer;

    @GetMapping(value = "/send-person")
    public void sendJson(@RequestParam String name, @RequestParam int year) {
        topicProducer.sendPerson(name, year);
    }
}