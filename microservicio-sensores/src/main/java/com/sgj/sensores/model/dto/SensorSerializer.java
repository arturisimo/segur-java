package com.sgj.sensores.model.dto;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgj.sensores.model.Sensor;

public class SensorSerializer implements Serializer<Sensor> {

	private static final Logger logger = LoggerFactory.getLogger(SensorSerializer.class);
	
	
	@Override
	public byte[] serialize(String topic, Sensor sensor) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
		    retVal = objectMapper.writeValueAsString(topic).getBytes();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return retVal;
	}

	

}

