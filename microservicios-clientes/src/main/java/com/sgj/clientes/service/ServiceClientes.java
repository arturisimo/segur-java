package com.sgj.clientes.service;

import java.util.List;

import com.sgj.clientes.model.Cliente;

public interface ServiceClientes {
	List<Cliente> clientes();
	Cliente obtenerCliente(Integer id);
	void eliminarCliente(Integer id);
	void guardarCliente(Cliente c);
}
