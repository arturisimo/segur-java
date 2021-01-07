package com.sgj.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgj.auth.model.Rol;

public interface RolRepository extends JpaRepository<Rol,Integer>{
	
	List<Rol> findByUsuario(Integer id);
	
	
}