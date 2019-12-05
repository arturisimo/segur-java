package com.sgj.web;


import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgj.web.model.Cliente;
import com.sgj.web.model.Sensor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

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
			ResponseEntity<Cliente[]> responseClient = restTemplate.getForEntity(urlCliente, Cliente[].class);
			Cliente[] clientesArray = responseClient.getBody();
			
			Cliente cliente = Arrays.asList(clientesArray).stream()
					.filter(c -> c.isPolicia())
					.findFirst()
					.orElseThrow(() -> new Exception("no hay clientes con servicio policia"));
			
			
			System.out.println("llamada get " + urlSensor+cliente.getId());
			ResponseEntity<Sensor[]> responseSensor = restTemplate.getForEntity(urlSensor+"sensores/"+cliente.getId(), Sensor[].class);
			List<Sensor> sensores = Arrays.asList(responseSensor.getBody());	
			
			sensores.stream().forEach(sensor-> {
				System.out.println("llamada a " + urlSensor+"provocarAlarma");
				HttpEntity<Sensor> request = new HttpEntity<>(sensor);
				ResponseEntity<String> responseAlarma = restTemplate.exchange(urlSensor+"provocarAlarma", HttpMethod.PUT, request, String.class);
				System.out.println(responseAlarma);
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	
	
}
