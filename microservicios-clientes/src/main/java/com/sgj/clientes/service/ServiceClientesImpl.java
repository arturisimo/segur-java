package com.sgj.clientes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgj.clientes.model.Cliente;
import com.sgj.clientes.repository.ClientesRepository;
@Service
public class ServiceClientesImpl implements ServiceClientes {
	
	@Autowired
	ClientesRepository clientesRepository;
	
	@Override
	public void eliminarCliente(Integer id) {
		if (clientesRepository.existsById(id)) {
			clientesRepository.deleteById(id);
		}
	}

	@Override
	public void guardarCliente(Cliente c) {
		clientesRepository.save(c);
	}

	@Override
	public List<Cliente> clientes() {
		return clientesRepository.findAll().stream()
				.filter(c->c.isEstado())
				.collect(Collectors.toList());
	}

	@Override
	public Cliente obtenerCliente(Integer id) {
		Cliente cliente = clientesRepository.findById(id).get();
		return cliente;
	}

	@Override
	public Cliente getClienteByIdUsuario(Integer idUsuario) {
		return clientesRepository.findByIdUsuario(idUsuario);
	}

}
