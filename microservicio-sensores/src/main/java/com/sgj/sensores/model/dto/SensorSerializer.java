package com.sgj.sensores.model.dto;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SensorSerializer implements Serializer<Mensaje> {

	private static final Logger logger = LoggerFactory.getLogger(SensorSerializer.class);
	
	
	@Override
	public byte[] serialize(String topic, Mensaje sensor) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
		    retVal = objectMapper.writeValueAsString(sensor).getBytes();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return retVal;
	}

	

}

