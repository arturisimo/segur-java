package com.sgj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgj.web.model.Usuario;
import com.sgj.web.service.ServiceUsuario;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {
	
	@Autowired
	ServiceUsuario serviceUsuario;

	@PostMapping(value="/alta-usuario", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> alta(@RequestBody Usuario usuarioPost) {
		Usuario usuario = serviceUsuario.save(usuarioPost);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	
}