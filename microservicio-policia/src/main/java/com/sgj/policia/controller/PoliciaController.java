package com.sgj.policia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgj.policia.model.Policia;
import com.sgj.policia.model.ResponseJson;
import com.sgj.policia.service.PoliciaService;

@CrossOrigin(origins = "*")
@RestController
public class PoliciaController {
	
	@Autowired
	PoliciaService policiaService;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> alta(@RequestBody String direccion) throws JsonProcessingException {
		ResponseJson response = new ResponseJson();
		ObjectMapper mapper = new ObjectMapper();
		try {
			policiaService.save(new Policia(direccion));
			response.setSuccess(true);
			response.setMessage("Se ha registrado correctamente");
			return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapper.writeValueAsString(response));
		}
	}
	
}
