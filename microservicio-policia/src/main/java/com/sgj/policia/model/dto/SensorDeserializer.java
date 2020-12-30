package com.sgj.policia.model.dto;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SensorDeserializer implements Deserializer<Sensor> {
	
	private static final Logger logger = LoggerFactory.getLogger(SensorDeserializer.class);
	
	@Override
	public Sensor deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		Sensor sensor = null;
	    try {
	    	sensor = mapper.readValue(data, Sensor.class);
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	    }
	    return sensor;
	}

}
