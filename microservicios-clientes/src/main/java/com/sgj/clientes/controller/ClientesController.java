package com.sgj.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgj.clientes.model.Cliente;
import com.sgj.clientes.service.ServiceClientes;

@CrossOrigin(origins = "*")
@RestController
public class ClientesController {
	@Autowired
	ServiceClientes serviceClientes;
	
	@GetMapping(value="/cliente/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Cliente buscarCliente(@PathVariable("id") Integer id) {
		Cliente cliente=serviceClientes.obtenerCliente(id);		
		return cliente;
	}
	
	@GetMapping(value="/clientes", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> listarClientes(){
		List<Cliente> lista = serviceClientes.clientes();
		return lista;
	}
	
	@DeleteMapping(value="/clientes/{id}",produces= MediaType.TEXT_PLAIN_VALUE)
	public void eliminar(@PathVariable("id") int idContacto) {
		serviceClientes.eliminarCliente(idContacto);
	}
	@PostMapping(value="/clientes",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void alta(@RequestBody Cliente cliente) {
		serviceClientes.guardarCliente(cliente);
	}
	@PutMapping(value="/clientes",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Cliente cliente) {
		serviceClientes.guardarCliente(cliente);
	}
	
}
