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
import com.sgj.sensores.model.ResponseJson;
import com.sgj.sensores.model.Sensor;
import com.sgj.sensores.service.ServiceSensores;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
public class SensoresController {
	
	@Autowired
	ServiceSensores serviceSensores;

	@GetMapping(value="sensores/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Sensor> list (@PathVariable("id") Integer id) {
		try {
			return serviceSensores.listadoByCliente(id);	
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ArrayList<>();
		}
	}
	
//	@CrossOrigin(origins="*")
//	@GetMapping(value="/continuo",produces="text/event-stream")
//	public Flux<List<Accion>> getContinuo(){
//		return Flux.create(fs->{
//			List<Accion> anterior=null;
//			while(true) {
//				List<Accion> lista = bolsaService.getAcciones();
//				if(cambio(anterior,lista)) {
//					fs.next(lista);
//				}
//				anterior=lista;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	private boolean cambio(List<Accion> anterior, List<Accion> actual) {
//		
//		if(anterior == null) {
//			return true;
//		} else {
//			for(int i=0;i<actual.size();i++) {
//				if(anterior.get(i).getValor()!=actual.get(i).getValor()){
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	
	@GetMapping(value="sensores/{id}", produces="text/event-stream")
	public Flux<List<Sensor>> listWF(@PathVariable("id") Integer id) {
		
		return Flux.create(fs->{
			List<Sensor> anterior=null;
			while(true) {
				List<Sensor> sensores = serviceSensores.findByCliente(id);	
				if(cambio(anterior, sensores)) {
					fs.next(sensores);
				}
				anterior = sensores;
			}
		});
		
	}
	
	private boolean cambio(List<Sensor> anterior, List<Sensor> actual) {
	
		if(anterior == null) {
			return true;
		} else {
			return anterior.equals(actual);
		}
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