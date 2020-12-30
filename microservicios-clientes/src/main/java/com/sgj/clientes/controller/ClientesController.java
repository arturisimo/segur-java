package com.sgj.clientes.controller;

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
import com.sgj.clientes.model.Cliente;
import com.sgj.clientes.service.ServiceClientes;
import com.sgj.commons.dto.ResponseJson;

@CrossOrigin(origins = "*")
@RestController
public class ClientesController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClientesController.class);
	
	@Autowired
	ServiceClientes serviceClientes;
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscarCliente(@PathVariable("id") Integer id) {
		Cliente cliente = serviceClientes.obtenerCliente(id);		
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@GetMapping(value="/usuario/{idUsuario}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscarClienteByUsuario(@PathVariable("idUsuario") Integer idUsuario) {
		Cliente cliente = serviceClientes.getClienteByIdUsuario(idUsuario);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> listarClientes(){
		List<Cliente> clientes = serviceClientes.clientes();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") int idContacto) {
		serviceClientes.eliminarCliente(idContacto);
		return new ResponseEntity<>("La eliminación es correcta", HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Cliente cliente) throws JsonProcessingException {
		ResponseJson response = new ResponseJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			serviceClientes.guardarCliente(cliente);
			response.setSuccess(true);
			response.setMessage("Se ha registrado correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapper.writeValueAsString(response));
		}
	}
	
	@PostMapping(value="/", consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestBody Cliente cliente) throws JsonProcessingException {
		ResponseJson response = new ResponseJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			serviceClientes.guardarCliente(cliente);
			response.setSuccess(true);
			response.setMessage("Se ha registrado correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapper.writeValueAsString(response));
		}
	}
	
	
	
//	@PutMapping(value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> actualizar(@RequestBody Cliente cliente) {
//		serviceClientes.guardarCliente(cliente);
//		return new ResponseEntity<>("Se ha registrado correctamente", HttpStatus.OK);
//	}
	
}
