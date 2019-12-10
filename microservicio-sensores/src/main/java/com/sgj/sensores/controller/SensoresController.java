package com.sgj.sensores.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.sgj.sensores.model.Alarma;
import com.sgj.sensores.model.Sensor;
import com.sgj.sensores.model.dto.ResponseJson;
import com.sgj.sensores.service.ServiceSensores;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
public class SensoresController {
	
	@Autowired
	ServiceSensores serviceSensores;

	@GetMapping(value="sensores-json/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Sensor> listEstatic(@PathVariable("id") Integer id) {
		try {
			return serviceSensores.listadoByCliente(id);	
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ArrayList<>();
		}
	}
	
	@GetMapping(value="sensores/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Sensor> list(@PathVariable("id") Integer id) {
		return Flux.fromIterable(serviceSensores.findByCliente(id));
	}
	
	@GetMapping(value="sensores/alarmas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Alarma> listAlarmas(@PathVariable("id") Integer id) {
		return serviceSensores.listadoAlarmasByCliente(id);	
	}
	
	@DeleteMapping(value="sensores/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete (@PathVariable("id") int id) throws JsonProcessingException{	
		ResponseJson response = new ResponseJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			serviceSensores.eliminarSensor(id);
			response.setSuccess(true);
			response.setMessage("Se ha eliminado correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapper.writeValueAsString(response));
		}
	}
		
	@PostMapping(value="sensores",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Sensor s) throws JsonProcessingException {	
		ResponseJson response = new ResponseJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			serviceSensores.crearSensor(s);
			response.setSuccess(true);
			response.setMessage("Se ha dado de alta correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapper.writeValueAsString(response));
		}
	}
	
	/**
	 * Actualiza/desactualiza sensor
	 * @param id
	 * @return
	 * @throws JsonProcessingException
	 */
	@PutMapping(value= "sensores/status/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateState(@PathVariable("id") int id) throws JsonProcessingException {	
		ResponseJson response = new ResponseJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			serviceSensores.actualizarEstadoSensor(id);
			response.setSuccess(true);
			response.setMessage("Se ha eliminado correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapper.writeValueAsString(response));
		}
	}
	
	@PutMapping(value= {"sensores", "sensores/{aviso}"}, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable("aviso") Optional<String> aviso, @RequestBody Sensor s) throws JsonProcessingException {	
		ResponseJson response = new ResponseJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			serviceSensores.actualizarSensor(s, aviso.isPresent());
			response.setSuccess(true);
			response.setMessage("Se ha eliminado correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapper.writeValueAsString(response));
		}
	}
	
	@PutMapping(value="provocarAlarma")
	public ResponseEntity<String> provocarAlarma (@RequestBody Sensor sensor){	
		try {
			serviceSensores.actualizarSensor(sensor, true);
			return new ResponseEntity<>("Alta de alarma", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Fallo en la eliminacion " +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}