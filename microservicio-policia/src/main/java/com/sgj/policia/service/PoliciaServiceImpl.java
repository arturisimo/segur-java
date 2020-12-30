package com.sgj.policia.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sgj.policia.model.Policia;
import com.sgj.policia.model.dto.Sensor;
import com.sgj.policia.repository.PoliciaRepository;

@Service
public class PoliciaServiceImpl {
	
	@Autowired
	PoliciaRepository policiaRepository;

	private final Logger logger = LoggerFactory.getLogger(PoliciaServiceImpl.class);
	
	@KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(Sensor sensor) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", sensor));
	
		Policia policia = new Policia(sensor.getDireccion());
		policiaRepository.save(policia);
	  
	}
	
}
