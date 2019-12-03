package com.sgj.clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgj.clientes.dao.DaoClientes;
import com.sgj.clientes.model.Cliente;
@Service
public class ServiceClientesImpl implements ServiceClientes {
	@Autowired
	DaoClientes daoClientes;
	
	@Override
	public void eliminarCliente(Integer id) {
		if (daoClientes.existsById(id)) {
			daoClientes.deleteById(id);
		}

	}

	@Override
	public void guardarCliente(Cliente c) {
		daoClientes.save(c);
	}

	@Override
	public List<Cliente> clientes() {
		List <Cliente> clientes = daoClientes.findAll();
		return clientes;
	}

	@Override
	public Cliente obtenerCliente(Integer id) {
		Cliente cliente = daoClientes.findById(id).get();
		return cliente;
	}

	@Override
	public Cliente getClienteByIdUsuario(Integer idUsuario) {
		return daoClientes.findByIdUsuario(idUsuario);
	}

}
