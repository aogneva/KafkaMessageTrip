package org.ogneva.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaConsumerApplication {

	public static void main(String[] args) {
//		try {
//			org.h2.tools.Console.main(args);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
