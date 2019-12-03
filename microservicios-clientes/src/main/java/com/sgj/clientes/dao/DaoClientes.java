package com.sgj.clientes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgj.clientes.model.Cliente;

public interface DaoClientes extends JpaRepository<Cliente,Integer> {

	Cliente findByIdUsuario(Integer idUsuario);
	

}
