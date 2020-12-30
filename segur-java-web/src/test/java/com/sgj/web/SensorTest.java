package com.sgj.web;


import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgj.commons.dto.ClienteDto;
import com.sgj.commons.dto.SensorDto;
import com.sgj.commons.enums.EstadoSensor;

@PropertySource("classpath:config.properties")
public class SensorTest {
	
	RestTemplate restTemplate;
	ObjectMapper objectMapper;
	
//	@Value("${url.servicio.sensores}")
//	String urlSensor;
//	
//	@Value("${url.servicio.clientes}")
//	String urlCliente;
	
	String urlSensor = "http://localhost:8100/alarmas/";
	String urlCliente = "http://localhost:9000/clientes/clientes";
	
	@Before
	public void init() {
		objectMapper = new ObjectMapper();
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void testAlarmaPolicia() throws Exception { 
		
		try {
		
			System.out.println("llamada get " + urlCliente);
			ResponseEntity<ClienteDto[]> responseClient = restTemplate.getForEntity(urlCliente, ClienteDto[].class);
			ClienteDto[] clientesArray = responseClient.getBody();
			
			ClienteDto cliente = Arrays.asList(clientesArray).stream()
					.filter(c -> c.isPolicia())
					.findFirst()
					.orElseThrow(() -> new Exception("no hay clientes con servicio policia"));
			
			
			System.out.println("llamada get " + urlSensor+"sensores-json/"+cliente.getId());
			ResponseEntity<SensorDto[]> responseSensor = restTemplate.getForEntity(urlSensor+"sensores-json/"+cliente.getId(), SensorDto[].class);
			List<SensorDto> sensores = Arrays.asList(responseSensor.getBody());	
			
			sensores.stream()
				.filter(s -> s.getEstado().equals(EstadoSensor.ACTIVADO))
				.forEach(sensor-> {
					System.out.println("llamada a " + urlSensor+"provocarAlarma");
					sensor.setEstadoBean(EstadoSensor.ALARMA);
					sensor.setDireccion(cliente.getDireccion());
					HttpEntity<SensorDto> request = new HttpEntity<>(sensor);
					ResponseEntity<String> responseAlarma = restTemplate.exchange(urlSensor+"provocarAlarma", HttpMethod.PUT, request, String.class);
					System.out.println(responseAlarma);
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	
	
}
