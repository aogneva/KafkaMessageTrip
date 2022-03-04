package org.ogneva.producer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KafkaController.class)
class KafkaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TopicProducer topicProducer;

    @Test
    @DisplayName("GET")
    void sendJson() throws Exception {

        Mockito.doNothing().when(topicProducer).sendPerson(any(), anyInt());
        mvc.perform(MockMvcRequestBuilders.get("/kafka/send-person")
                        .param("name", "Karina")
                        .param("year", "2005")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}