package com.sgj.auth.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgj.auth.model.Usuario;
import com.sgj.auth.service.UserService;


@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {
	
	@Autowired
	UserService userService;
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);

	@PostMapping(value="/alta-usuario", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> alta(@RequestBody Usuario usuarioPost) {
		try {
			Usuario usuario = userService.save(usuarioPost);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(new Usuario(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@DeleteMapping(value="/usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		try {
			userService.delete(id);
			return new ResponseEntity<>("Se ha eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	
	@GetMapping(value="/usuario/{nombreUsuario:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> get(@PathVariable String nombreUsuario) {
		try {
			Usuario usuario = userService.findByUsuario(nombreUsuario);
			return new ResponseEntity<>(usuario, HttpStatus.OK);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(new Usuario(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}