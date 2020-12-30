package com.sgj.policia.model.dto;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgj.commons.dto.Mensaje;

public class SensorDeserializer implements Deserializer<Mensaje> {
	
	private static final Logger logger = LoggerFactory.getLogger(SensorDeserializer.class);
	
	@Override
	public Mensaje deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		Mensaje sensor = null;
	    try {
	    	sensor = mapper.readValue(data, Mensaje.class);
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	    }
      	    return sensor;
	}

}
