package com.sgj.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgj.web.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	
	
}