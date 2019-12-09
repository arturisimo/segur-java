package com.sgj.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgj.clientes.model.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente,Integer> {

	Cliente findByIdUsuario(Integer idUsuario);
	

}
