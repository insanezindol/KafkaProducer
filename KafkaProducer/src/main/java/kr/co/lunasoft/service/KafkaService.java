package kr.co.lunasoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String topic, String message) {
		log.info("[PRODUCER] '{}' = '{}'", topic, message);
		kafkaTemplate.send(topic, message);
	}

}
