package com.sgj.sensores.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgj.commons.dto.ResponseJson;
import com.sgj.commons.enums.EstadoSensor;
import com.sgj.sensores.model.Alarma;
import com.sgj.sensores.model.Sensor;
import com.sgj.sensores.service.ServiceSensores;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
public class SensoresController {
	
	@Autowired
	ServiceSensores serviceSensores;
	
	private static final Logger LOG = LoggerFactory.getLogger(SensoresController.class);
	
	@GetMapping(value="/sensores-json/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Sensor> listEstatic(@PathVariable("id") Integer id) {
		try {
			return serviceSensores.listadoByCliente(id);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new ArrayList<>();
		}
	}
	
	@GetMapping(value="/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Sensor> list(@PathVariable("id") Integer id) {
		return Flux.fromIterable(serviceSensores.findByCliente(id));
	}
	
	@GetMapping(value="/alarmas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Alarma> listAlarmas(@PathVariable("id") Integer id) {
		return serviceSensores.listadoAlarmasByCliente(id);	
	}
	
	@DeleteMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete (@PathVariable("id") int id) throws JsonProcessingException{	
		ResponseJson response = new ResponseJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			serviceSensores.eliminarSensor(id);
			response.setSuccess(true);
			response.setMessage("Se ha eliminado correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapper.writeValueAsString(response));
		}
	}
		
	@PostMapping(value="/", consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sensor> save(@RequestBody Sensor s) throws JsonProcessingException {	
		ResponseJson response = new ResponseJson();
		try {
			serviceSensores.altaSensor(s);
			response.setSuccess(true);
			response.setMessage("Se ha dado de alta correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(s);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Sensor());
		}
	}
	
	/**
	 * Actualiza/desactualiza sensor
	 * @param id
	 * @return
	 * @throws JsonProcessingException
	 */
	@PutMapping(value= "/status/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sensor> updateState(@PathVariable("id") int id) throws JsonProcessingException {	
		ResponseJson response = new ResponseJson();
		try {
			Sensor sensor = serviceSensores.getById(id);
			sensor.setEstado(EstadoSensor.DESACTIVADO.equals(sensor.getEstado()) ? EstadoSensor.ACTIVADO : EstadoSensor.DESACTIVADO);
			
			serviceSensores.update(sensor);
			response.setSuccess(true);
			response.setMessage("Se ha eliminado correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(sensor);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Sensor());
		}
	}
	
	@PutMapping(value="/alarma")
	public ResponseEntity<String> alarma(@RequestBody Sensor sensor){	
		try {
			sensor.setEstado(EstadoSensor.ALARMA);
			sensor.setAlarmas(new ArrayList<>());
			serviceSensores.update(sensor);
			return new ResponseEntity<>("Alta de alarma", HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>("Fallo al provocar alarma " +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}