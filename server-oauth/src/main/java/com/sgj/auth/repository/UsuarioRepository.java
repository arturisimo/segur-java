package com.sgj.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgj.auth.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	Usuario findByUsuario(String nombreUsuario);
	
	
}